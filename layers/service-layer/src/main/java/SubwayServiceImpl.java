import Utils.SubwayUtils;
import com.google.inject.Inject;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by cerokuo on 08/03/2017.
 */
public class SubwayServiceImpl implements SubwayService {

    public static final String STATION_NOT_FOUND = "The station does not exist";
    public static final String DIRECTION_NOT_FOUND = "The direction is not correct";
    public static final String LINE_NOT_FOUND = "The station does not have the line requested.";

    @Inject
    private Subway subway;

    private Station station;


    @Override
    public String getNextTrainTime(final TrainQueryParams params) throws InvalidParameterException{
        if (!subway.stationExists(params.getStationName())) {
            throw new InvalidParameterException(STATION_NOT_FOUND);
        }
        station = subway.getStation(params.getStationName());
        if(!station.lineExists(params.getLineName())) {
            throw new InvalidParameterException(LINE_NOT_FOUND);
        }
        return getNextTrainInfo(params.getLineName(), params.getDirection(), params.getTime().orElse(SubwayUtils.getCurrentTime()));

    }


    private String getNextTrainInfo(String lineName, String direction, LocalTime time) {
        List<LocalTime> scheduleDayTime = station.getScheduleList(lineName, direction);
        if(null == scheduleDayTime) {
            throw new InvalidParameterException(DIRECTION_NOT_FOUND);
        }
        return SubwayUtils.getTimeFormated(findNextTrainAtTime(scheduleDayTime, time));

    }

    private LocalTime findNextTrainAtTime(List<LocalTime> scheduleDayTime, LocalTime time) {
        return calculateNextTimeSchedule(scheduleDayTime, time);
    }


    private LocalTime calculateNextTimeSchedule(List<LocalTime> scheduleDayTime, LocalTime currentTime) {
        int end = scheduleDayTime.size() - 1;
        int start = 0;
        while(start <= end) {
            int pivot = end - (end - start)/2;

            if(scheduleDayTime.get(pivot).compareTo(currentTime) == 0){
                if(pivot < scheduleDayTime.size()-1) {
                    return scheduleDayTime.get(pivot + 1);
                } else {
                    //no more trains after
                    return scheduleDayTime.get(0);
                }
            }
            if(pivot < scheduleDayTime.size() -1) {
                if(scheduleDayTime.get(pivot).isBefore(currentTime) && scheduleDayTime.get(pivot +1).isAfter(currentTime)) {
                    return scheduleDayTime.get(pivot +1);
                }
            }

            if(scheduleDayTime.get(pivot).isAfter(currentTime)) {
                end = pivot - 1;
            } else {
                start = pivot + 1;
            }
        }
        return scheduleDayTime.get(0);
    }

    public void setSubway(Subway subway) {
        this.subway = subway;
    }
}
