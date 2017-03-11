import com.subway.got.*;
import com.subway.got.Utils.SubwayUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.InvalidParameterException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Created by cerokuo on 08/03/2017.
 */
@Test
public class SubwayServiceImplTest {

    private static final String STATION_NAME = "Spadina";
    private static final String LINE_NAME = "Line2";

    @Mock
    private Subway subway;

    private Station station;
    private TrainQueryParams paramsWithTime;
    private SubwayServiceImpl subwayServiceImpl;
    private TrainQueryParams paramsWithOutTime;


    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subwayServiceImpl = new SubwayServiceImpl();
        subwayServiceImpl.setSubway(subway);

        Map<String, Map<String, List<LocalTime>>> scheduleInfo = new HashMap<String, Map<String, List<LocalTime>>>();
        Map<String, List<LocalTime>> directionAndTime = new HashMap<String, List<LocalTime>>();


        ArrayList<LocalTime> times = new ArrayList<LocalTime>();
        times.add(LocalTime.of(11, 15, 00));
        times.add(LocalTime.of(12, 15, 00));
        times.add(LocalTime.of(12, 18, 00));
        times.add(LocalTime.of(12, 20, 00));
        times.add(LocalTime.of(12, 23, 00));
        times.add(LocalTime.of(15, 22, 00));
        times.add(LocalTime.of(23, 01, 00));

        directionAndTime.put(Direction.NORTH.toString(), times);
        directionAndTime.put(Direction.SOUTH.toString(), times);

        scheduleInfo.put(LINE_NAME, directionAndTime);


        station = new Station();
        station.setStationName(STATION_NAME);
        station.setInfoStation(scheduleInfo);


        paramsWithTime = generateParams(Direction.NORTH.toString(), LINE_NAME, STATION_NAME, 16, 00, 00);

        paramsWithOutTime = new TrainQueryParams.TrainQueryParamsBuilder()
                .direction(Direction.SOUTH.toString())
                .lineName(LINE_NAME)
                .stationName(STATION_NAME)
                .build();

        when(subway.stationExists(STATION_NAME)).thenReturn(true);
        when(subway.getStation(STATION_NAME)).thenReturn(station);

    }

    public void getInfoNextTrainNoTimeTest() {
        assertEquals(subwayServiceImpl.getNextTrainTime(paramsWithOutTime), SubwayUtils.getTimeFormated(LocalTime.of(23, 01, 00)));
    }

    public void getInfoNextTrainWithSpecificTimeTest() {
        assertEquals(subwayServiceImpl.getNextTrainTime(paramsWithTime), SubwayUtils.getTimeFormated(LocalTime.of(23, 01, 00)));
    }

    public void getInfoNextTrainSameTimeAsScheduleTest() {
        paramsWithTime = generateParams(Direction.NORTH.toString(), LINE_NAME, STATION_NAME, 12, 23, 00);
         assertEquals(subwayServiceImpl.getNextTrainTime(paramsWithTime),SubwayUtils.getTimeFormated(LocalTime.of(15, 22, 00)));
    }

    public void getInfoNextTrainTimeOutOfRangeTest() {
        paramsWithTime = generateParams(Direction.SOUTH.toString(), LINE_NAME, STATION_NAME, 00, 55, 00);
        assertEquals(subwayServiceImpl.getNextTrainTime(paramsWithTime), SubwayUtils.getTimeFormated(LocalTime.of(11, 15, 00)));
    }
    @Test(expectedExceptions = InvalidParameterException.class)
    public void getInfoNextTrainNonExistingStationTest(){
        TrainQueryParams paramsWithOutStationName = generateParams(Direction.SOUTH.toString(), LINE_NAME, "", 00, 55, 00);
        subwayServiceImpl.getNextTrainTime(paramsWithOutStationName);
    }

    @Test(expectedExceptions = InvalidParameterException.class)
    public void getInfoNextTrainIncorrectDirectionTest() {
        TrainQueryParams paramsWithoutDirection = generateParams("", LINE_NAME, STATION_NAME, 00, 55, 00);
        subwayServiceImpl.getNextTrainTime(paramsWithoutDirection);
    }
    @Test(expectedExceptions = InvalidParameterException.class)
    public void getInfoNextTrainIncorrectLineTest(){
        TrainQueryParams paramsWithOutLineName = generateParams(Direction.SOUTH.toString(), "", STATION_NAME, 00, 55, 00);
        subwayServiceImpl.getNextTrainTime(paramsWithOutLineName);
    }

    private TrainQueryParams generateParams(String dir, String line, String station, int hour, int min, int second) {
        return new TrainQueryParams.TrainQueryParamsBuilder()
                .direction(dir)
                .lineName(line)
                .stationName(station)
                .time(LocalTime.of(hour, min, second))
                .build();
    }

}