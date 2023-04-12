package Core;

public class Station {
    private String name;
    private String numberLine;
    private double depth = 999;
    private String date;
    private boolean haveConnection;

    public Station(String name, String numberLine, boolean haveConnection) {
        this.name = name;
        this.numberLine = numberLine;
        this.haveConnection = haveConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHaveConnection() {
        return haveConnection;
    }

    public void setHaveConnection(boolean haveConnection) {
        this.haveConnection = haveConnection;
    }

    @Override
    public String toString() {
        return String.format("%-43s%-43s%s",
                "Название станции: " + name,
                "№ линии: " + numberLine +
                        (depth == 999 ? "" : "\tГлубина станции: " + depth),
                date == null ? "" : "Дата открытия: " + date);
    }
}