import java.time.LocalTime;
import java.util.Optional;

/**
 * Created by cerokuo on 09/03/2017.
 */
public class TrainQueryParams {


    private final Optional<LocalTime> time;
    private final String stationName;
    private final String lineName;
    private final String direction;

    public Optional<LocalTime> getTime() {
        return time;
    }

    public String getStationName() {
        return stationName;
    }

    public String getLineName() {
        return lineName;
    }

    public String getDirection() {
        return direction;
    }


    public static class TrainQueryParamsBuilder{
        private Optional<LocalTime> time;
        private String stationName;
        private String lineName;
        private String direction;

        public TrainQueryParamsBuilder() {
            this.time = Optional.empty();
        }

        public TrainQueryParamsBuilder time (LocalTime newTime) {
            this.time = Optional.of(newTime);
            return this;
        }

        public TrainQueryParamsBuilder stationName( String newStationName) {
            this.stationName = newStationName;
            return this;
        }

        public TrainQueryParamsBuilder  lineName( String newLineName) {
            this.lineName = newLineName;
            return this;
        }

        public TrainQueryParamsBuilder direction( String newDirection) {
            this.direction = newDirection;
            return this;
        }

        public TrainQueryParams build() {
            return new TrainQueryParams(this);

        }

    }

    private TrainQueryParams (TrainQueryParamsBuilder builder) {
        this.direction = builder.direction;
        this.time = builder.time;
        this.lineName = builder.lineName;
        this.stationName = builder.stationName;
    }





}
