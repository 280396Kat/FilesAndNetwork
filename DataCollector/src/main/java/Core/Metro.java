package Core;

import Utils.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class Metro {
    private static TreeMap<Line, ArrayList<Station>> metro = new TreeMap<>(new SortAscendingComparator());

    public Metro(String pathToHtml, String pathDirectory) throws IOException, ParseException {
        ParserWeb.parsingSite(pathToHtml);
        FindFiles files = new FindFiles(pathDirectory);
        for (String s : files.getNameJSONFiles()) {
            ParserJSON.parserFiles(s);
            System.out.println(s);
        }
        for (String s : files.getNameCSVFiles()) {
            ParserCSV.parserFiles(s);
            System.out.println(s);
        }
        createMetro();
    }

    private void createMetro() {
        ArrayList<Station> allStationsFromWeb = ParserWeb.getAllStations();
        ArrayList<Station> stationsFromWebAndJSON = unionWithJSON(allStationsFromWeb);
        ArrayList<Station> stationsFromWebJSONAndCSV = unionWithCSV(stationsFromWebAndJSON);
        metro = unionWithLines(stationsFromWebJSONAndCSV);
    }

    private ArrayList<Station> unionWithJSON(ArrayList<Station> stationList) {
        ArrayList<StationFromJson> stationsFromJSON = ParserJSON.getStations();
        stationList.forEach(station -> stationsFromJSON.forEach(stfj -> {
            if (station.getName().equals(stfj.getName()) && station.getDepth() == 999) {
                station.setDepth(stfj.getDEPTH());
                stfj.setName(stfj.getName() + " used");
            }
        }));
        return stationList;
    }

    private ArrayList<Station> unionWithCSV(ArrayList<Station> stationsList) {
        ArrayList<StationFromCSV> stationsFromCSVS = ParserCSV.getStations();
        stationsList.forEach(station -> stationsFromCSVS.stream()
                .filter(st -> station.getName().equals(st.getName()) && station.getDate() == null).
                forEach(stfc -> {
                    station.setDate(stfc.getDate());
                    stfc.setName(stfc.getName() + " used");
                }));
        return stationsList;
    }

    private TreeMap<Line, ArrayList<Station>> unionWithLines(ArrayList<Station> stations) {
        TreeMap<Line, ArrayList<Station>> linesAndStations = new TreeMap<>(new SortAscendingComparator());
        ArrayList<Line> allLinesFromWeb = ParserWeb.getAllLines();
        allLinesFromWeb.forEach(line -> {
                    ArrayList<Station> stationList = new ArrayList<>(stations.stream()
                            .filter(station -> line.getNumber().equals(station.getNumberLine())).toList());
                    linesAndStations.put(line, stationList);
                }
        );
        return linesAndStations;
    }

    public TreeMap<Line, ArrayList<Station>> getMetro() {
        return new TreeMap<>(metro);
    }
}