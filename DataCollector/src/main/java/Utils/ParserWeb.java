package Utils;

import Core.Line;
import Core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ParserWeb {
    private static final ArrayList<Line> allLines = new ArrayList<>();
    private static final ArrayList<Station> allStations = new ArrayList<>();

    public static void parsingSite(String siteName) throws IOException {
        Document doc = Jsoup.connect(siteName).get();
        Elements line = doc.select(".js-metro-line");
        Elements station = doc.select("div.js-metro-stations");
        station.forEach(e -> (e.select("span.name"))
                .forEach(a -> allStations.add(parserStation(a, e.attr("data-line"), a.parent()))));
        line.forEach(e -> allLines.add(parserLine(e)));
        allLines.forEach(l -> allStations.stream().filter(s -> l.getNumber().equals(s.getNumberLine()))
                .forEach(s -> l.getStationList().add(s)));
    }

    public static Line parserLine(Element e) {
        return new Line(e.text(), e.attr("data-line"));
    }

    public static Station parserStation(Element e, String number, Element p) {
        return new Station(e.text(), number, !p.select("span.t-icon-metroln").isEmpty());
    }

    public static ArrayList<Line> getAllLines() {
        return new ArrayList<>(allLines);
    }

    public static ArrayList<Station> getAllStations() {
        return new ArrayList<>(allStations);
    }
}
