package Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by cerokuo on 08/03/2017.
 */
public class SubwayUtils {

    public static final String DATE_FORMAT = "HH:mm:ss";

    public static String getTimeFormated(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.CANADA);
        return time.format(formatter);
    }

    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

}
