package util;

import java.sql.Date;
import java.util.Calendar;

public class DateGenerator {
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        return new Date(currentDate.getTime());
    }

    public static Date getNextMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        java.util.Date nextMonthDate = calendar.getTime();
        return new Date(nextMonthDate.getTime());
    }
}
