package Utils;

import Core.StationFromCSV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserCSV {
    private static final List<StationFromCSV> stations = new ArrayList<>();

    public static void parserFiles(String absPath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(absPath));
        lines = lines.subList(1, lines.size());
        lines.forEach(e -> {
            String[] components = e.split(",");
            stations.add(new StationFromCSV(components[0], components[1]));
        });
    }

    public static ArrayList<StationFromCSV> getStations() {
        return new ArrayList<>(stations);
    }
}
