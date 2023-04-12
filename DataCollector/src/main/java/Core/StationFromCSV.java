package Core;

public class StationFromCSV {
    private String name;
    private String date;

    public StationFromCSV(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Название станции: " + name + "\tДата открытия: " + date;
    }
}
