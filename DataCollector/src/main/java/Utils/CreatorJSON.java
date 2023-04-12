package Utils;

import Core.Metro;

import com.google.gson.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreatorJSON {

    public static void creator(Metro metro, Path pathMap, Path pathStations) {

        JsonObject metroMap = new JsonObject();
        JsonObject stations = new JsonObject();
        JsonArray lines = new JsonArray();
        JsonObject stationList = new JsonObject();
        JsonArray stationsInfo = new JsonArray();
        metro.getMetro().forEach((k, v) -> {
            if (k.getNumber().matches("0\\d")) k.setNumber(k.getNumber().replaceAll("0", ""));
            JsonArray stationNames = new JsonArray();
            v.forEach(e -> stationNames.add(e.getName()));
            stations.add(k.getNumber(), stationNames);
            JsonObject lineInfo = new JsonObject();
            lineInfo.addProperty("name", k.getName());
            lineInfo.addProperty("number", k.getNumber());
            lines.add(lineInfo);
            v.forEach(s -> {
                JsonObject stInfo = new JsonObject();
                stInfo.addProperty("name", s.getName());
                stInfo.addProperty("line", k.getName());
                if (s.getDate() != null) stInfo.addProperty("date", s.getDate());
                if (s.getDepth() != 999) stInfo.addProperty("depth", s.getDepth());
                stInfo.addProperty("hasConnection", s.isHaveConnection());
                stationsInfo.add(stInfo);
            });
        });
        metroMap.add("stations", stations);
        metroMap.add("lines", lines);
        stationList.add("stations", stationsInfo);
        Gson metroLineWithStationsGson = new GsonBuilder().setPrettyPrinting().create();
        String metroLineWithStations = metroLineWithStationsGson.toJson(metroMap);
        Gson stationsGson = new GsonBuilder().setPrettyPrinting().create();
        String stationsWrite = stationsGson.toJson(stationList);

        try {
            Files.write(pathMap, metroLineWithStations.getBytes());
            Files.write(pathStations, stationsWrite.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
