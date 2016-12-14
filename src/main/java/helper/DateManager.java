package helper;

import java.util.Date;
import java.util.Formatter;

/**
 * Created by Ksenia on 10.12.2016.
 */
public class DateManager {

    private static Date date;
    private static Formatter formatter;

    public DateManager() {
        date = new Date();
        formatter = new Formatter();
    }

    public String toString() {
        formatter.format("%tD %tH:%tM:%tS", date, date, date, date);
        return "Subject " + formatter.toString();
    }

}
