import java.util.List;
import java.util.Map;

/**
 * Created by cerokuo on 09/03/2017.
 */
public class Line {

    //line name, listofStations
    private String lineName;

    //direction, stations
    private Map<String, List<String>> linesStations;


    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Map<String, List<String>> getLinesStations() {
        return linesStations;
    }

    public void setLinesStations(Map<String, List<String>> linesStations) {
        this.linesStations = linesStations;
    }

    @Override
    public int hashCode() {
        return lineName.hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return lineName.equals(line.lineName);

    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + lineName + '\'' +
                ", linesStations=" + linesStations +
                '}';
    }

}
