package io.github.biezhi.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * LocalTime 示例
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class LocalTimeExample {

    public static void main(String[] args) {

        // 创建一个LocalTime实例
        LocalTime localTime = LocalTime.now();

        // 使用指定的时分秒和纳秒来新建对象
        LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);

        // 3小时后
        LocalTime localTimeLater = localTime.plusHours(3);
        // 3小时前
        LocalTime localTimeEarlier = localTime.minusHours(3);

        System.out.println("localTime       : " + localTime);
        System.out.println("localTime2      : " + localTime2);
        System.out.println("localTimeLater  : " + localTimeLater);
        System.out.println("localTimeEarlier: " + localTimeEarlier);
    }
}
