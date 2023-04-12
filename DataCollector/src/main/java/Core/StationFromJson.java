package Core;

public class StationFromJson {

    private String name;
    private final double DEPTH;

    public StationFromJson(String name, double DEPTH) {
        this.name = name;
        this.DEPTH = DEPTH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDEPTH() {
        return DEPTH;
    }

    @Override
    public String toString() {
        return "Название станции: " + name + "\tГлубина станции: " + DEPTH;
    }
}
