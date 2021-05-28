package io.mvvm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: io-admin
 * @description: 时间工具
 * @author: Mr. Pan
 * @create: 2021-05-29 01:55
 **/
public class DateUtil {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

}
