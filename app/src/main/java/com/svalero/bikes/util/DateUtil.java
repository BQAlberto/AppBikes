package com.svalero.bikes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static Date format(String date) throws ParseException {
        return new SimpleDateFormat(DATE_PATTERN, Locale.FRANCE).parse(date);
    }

}
