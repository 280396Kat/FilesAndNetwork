package Utils;

import Core.Line;

import java.util.Comparator;

public class SortAscendingComparator implements Comparator<Line> {

    @Override
    public int compare(Line o1, Line o2) {
        if (o1.getNumber().matches("\\d")) {
            o1.setNumber("0" + o1.getNumber());
        }
        if (o2.getNumber().matches("\\d")) {
            o2.setNumber("0" + o2.getNumber());
        }
        return o1.getNumber().compareTo(o2.getNumber());
    }
}
