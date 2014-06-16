package ua.ros.taxiapp.formatters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatter implements Formatter<Date> {

    public DateTimeFormatter() {
        super();
    }

    public String print(Date date, Locale locale) {
        return date.getHours() + " " + date.getMinutes();
    }

    public Date parse(String source, Locale locale)
            throws ParseException {
        int area = Integer.parseInt(source.substring(0, 3));
        int group = Integer.parseInt(source.substring(4, 6));
        int serial = Integer.parseInt(source.substring(7, 11));
        return new Date(area, group, serial);
    }
}