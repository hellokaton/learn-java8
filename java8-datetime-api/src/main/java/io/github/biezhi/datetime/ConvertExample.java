package io.github.biezhi.datetime;

import java.time.*;
import java.util.Date;

/**
 * 日期转换示例
 *
 * @author biezhi
 * @date 2018/3/3
 */
public class ConvertExample {

    /**
     * LocalDate -> Date
     *
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        ZoneId  zone    = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId  zone    = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalTime -> Date
     *
     * @param localTime
     * @return
     */
    public static Date toDate(LocalTime localTime) {
        LocalDate     localDate     = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId        zone          = ZoneId.systemDefault();
        Instant       instant       = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * Date -> LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        Instant       instant       = date.toInstant();
        ZoneId        zone          = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId  zone    = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * Date -> LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime toLocalTime(Date date) {
        Instant       instant       = date.toInstant();
        ZoneId        zone          = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }


}
