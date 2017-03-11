import java.security.InvalidParameterException;

/**
 * Created by cerokuo on 09/03/2017.
 */
public interface SubwayService {

    /**
     * Return the time of the next train to arrive from current time.
     * @param params
     * @return
     */

    String getNextTrainTime(TrainQueryParams params) throws InvalidParameterException;
}
