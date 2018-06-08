package com.merchantesolutions.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

public Date getDate(String dateString, String format) {
    try {
        DateFormat formatter = new SimpleDateFormat(format);
        Date date = formatter.parse(dateString);
        return date;
    } catch (Exception e) {
        System.out.println("Exception in date conversion.");
        e.printStackTrace();
    }
    return null;
}

}
