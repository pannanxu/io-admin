package io.mvvm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @program: io-admin
 * @description: 时间工具
 * @author: 潘南旭
 * @create: 2021-05-29 01:55
 **/
public class DateUtil {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取日历
     */
    private static final Calendar CALENDAR = Calendar.getInstance();

    /**
     * 获取精确到秒的时间戳
     *
     * @param date Date
     * @return 时间戳
     */
    public static int getSecondTimestampTwo(Date date) {
        return ConvertUtil.parseInteger(date.getTime() / 1000);
    }

    /**
     * 获取当前时间的秒级时间戳
     *
     * @return 时间戳
     */
    public static int getSecondTimestampTwo() {
        Date date = new Date();
        return ConvertUtil.parseInteger(date.getTime() / 1000);
    }

    /**
     * 获取下 N 年的日期
     *
     * @param year 年数
     * @return {year} 年后的日期
     */
    public static String getNextYear(int year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        LocalDateTime timeNow = LocalDateTime.now();
        timeNow = timeNow.plusYears(year);
        return timeNow.format(formatter);
    }

    /**
     * 获取下一年的日期
     *
     * @return 下一年的日期
     */
    public static String getNextYear() {
        return getNextYear(1);
    }

    /**
     * 获取下n天的日期
     * @param amount 天
     * @return       {amount} 天后的日期
     */
    public static Date getNextDay(int amount) {
        CALENDAR.add(Calendar.DATE, amount);
        return CALENDAR.getTime();
    }

    /**
     * 格式化日期
     * @param date 日期
     * @return     格式化后的日期
     */
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        return sdf.format(date);
    }

    /**
     * 格式化日期
     * @param date 日期
     * @return     格式化后的日期
     */
    public static Date format(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
