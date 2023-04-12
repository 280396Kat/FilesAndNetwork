package Core;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private String name;
    private String number;
    private List<Station> stationList;

    public Line(String name, String number) {
        this.name = name;
        this.number = number;
        stationList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    @Override
    public String toString() {
        return "№: " + number + " " + name;
    }
}
