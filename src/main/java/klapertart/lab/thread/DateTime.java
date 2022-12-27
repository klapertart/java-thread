package klapertart.lab.thread;

import java.util.Calendar;
import java.util.Date;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class DateTime {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR,1922);
        calendar.set(Calendar.MONTH,Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH,5);

        Date date = calendar.getTime();
        System.out.println(date);
    }
}
