package com.subway.got;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * Created by cerokuo on 08/03/2017.
 */
public class Station {

    private String stationName;
    //<lineName, <direction, list<hh:mm:ss>>
    private Map<String,Map<String, List<LocalTime>>> infoStation;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Map<String, Map<String, List<LocalTime>>> getInfoStation() {
        return infoStation;
    }

    public void setInfoStation(Map<String, Map<String, List<LocalTime>>> infoStation) {
        this.infoStation = infoStation;
    }

    public List<LocalTime> getScheduleList(String line, final String direction) {
        return infoStation.get(line).get(direction);
    }

    public boolean lineExists(String line) {
        return infoStation.containsKey(line);
    }

    @Override
    public int hashCode() {
        return stationName.hashCode();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return stationName.equals(station.stationName);

    }

    @Override
    public String toString() {
        return "com.subway.got.Station{" +
                "name='" + stationName + '\'' +
                ", infoStations=" + infoStation +
                '}';
    }


}
