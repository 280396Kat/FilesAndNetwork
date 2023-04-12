import Core.Metro;
import Utils.CreatorJSON;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String pathHTML = "https://skillbox-java.github.io/";
        String pathDirectory = "src/main/resources/data";
        Path outputPath = Path.of("output/map.json");
        Path outputPathStations = Path.of("output/stationsList.json");
        Metro metro = new Metro(pathHTML, pathDirectory);
        CreatorJSON.creator(metro, outputPath, outputPathStations);

        StringBuilder stations = new StringBuilder();
        metro.getMetro().forEach((k, v) -> {
            stations.append(k.getName()).append(":\n");
            v.forEach(s -> stations.append(s).append("\n"));
            stations.append("\n");
        });
        System.out.println(stations);
    }
}