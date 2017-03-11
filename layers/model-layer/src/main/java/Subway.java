import java.util.HashMap;
import java.util.Map;

/**
 * Created by cerokuo on 08/03/2017.
 */
public class Subway {

    private String subwayName;
    private Map<String, Line> linesStations;
    private Map<String, Station> stations;

    public Map<String, Line> getLinesStations() {
        return linesStations;
    }

    public void setLinesStations(Map<String, Line> linesStations) {
        this.linesStations = linesStations;
    }

    public Map<String, Station> getStations() {
        return stations;
    }

    public void setStations(HashMap<String, Station> stations) {
        this.stations = stations;
    }

    public boolean stationExists(String station) {
        return stations.containsKey(station);
    }

    public String getSubwayName() {
        return subwayName;
    }

    public void setSubwayName(String subwayName) {
        this.subwayName = subwayName;
    }

    public Station getStation(String name) {
        return stations.get(name);
    }

    @Override
    public int hashCode() {
        return subwayName.hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subway subway = (Subway) o;
        return subwayName.equals(subway.subwayName);

    }

    @Override
    public String toString() {
        return "Subway{" +
                "name='" + subwayName + '\'' +
                ", linesStations=" + linesStations +
                ", stations=" + stations +
                '}';
    }

}
