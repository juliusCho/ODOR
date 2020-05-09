package com.back.odor.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String timeZone = Calendar.getInstance().getTimeZone().getID();

    public static Date convertToLocalDate(Date inputDate) {
        return new Date(inputDate.getTime() + TimeZone.getTimeZone(timeZone).getOffset(inputDate.getTime()));
    }

    public static String getLocalDate(Date date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String result = "";
        try {
            result = format.format(convertToLocalDate(date));
        } catch (Exception e) {
            result = "";
        } finally {
            return result;
        }
    }

    public static String getLocalDateTime(Date date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        String result = "";
        try {
            result = format.format(convertToLocalDate(date));
        } catch (Exception e) {
            result = "";
        } finally {
            return result;
        }
    }

}
