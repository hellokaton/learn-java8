package io.github.biezhi.datetime;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * ZonedDateTime 示例
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class ZonedDateTimeExample {

    public static void main(String[] args) {
        // 创建一个ZonedDateTime实例
        ZonedDateTime dateTime = ZonedDateTime.now();

        // 使用指定的年月日、时分秒、纳秒以及时区ID来新建对象
        ZoneId        zoneId    = ZoneId.of("UTC+8");
        ZonedDateTime dateTime2 = ZonedDateTime.of(2018, 3, 8, 23, 45, 59, 1234, zoneId);

        // 3天后
        ZonedDateTime zoneDateTime = dateTime2.plus(Period.ofDays(3));

        ZoneId zoneId2 = ZoneId.of("Europe/Copenhagen");
        ZoneId zoneId3 = ZoneId.of("Europe/Paris");

        System.out.println("dateTime     : " + dateTime);
        System.out.println("zoneDateTime : " + zoneDateTime);
        System.out.println("zoneId2      : " + zoneId2);
        System.out.println("zoneId3      : " + zoneId3);
    }
}
