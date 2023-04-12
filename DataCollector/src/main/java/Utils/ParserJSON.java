package Utils;

import Core.StationFromJson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserJSON {
    private static final List<StationFromJson> stations = new ArrayList<>();

    public static void parserFiles(String absPath) throws ParseException, IOException {
        Path path = Path.of(absPath);
        JSONParser parser = new JSONParser();
        JSONArray jsonData = (JSONArray) parser.parse(getFile(path));
        jsonData.forEach(jso -> {
            JSONObject station = (JSONObject) jso;
            String depthString = station.get("depth").toString().replaceAll("-", "-")
                    .replaceAll(",", ".");
            double depth = !depthString.equals("?") ? Double.parseDouble(depthString) : 999;
            stations.add(new StationFromJson(station.get("station_name").toString(), depth));
        });
    }

    private static String getFile(Path path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> lines = Files.readAllLines(path);
        lines.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public static ArrayList<StationFromJson> getStations() {
        return new ArrayList<>(stations);
    }
}