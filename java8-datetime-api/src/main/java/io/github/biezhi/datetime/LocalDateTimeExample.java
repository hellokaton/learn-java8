package io.github.biezhi.datetime;

import java.time.LocalDateTime;

/**
 * LocalDateTime 示例
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class LocalDateTimeExample {

    public static void main(String[] args) {

        // 创建一个LocalDateTime实例
        LocalDateTime localDateTime = LocalDateTime.now();

        // 使用指定的年月日、时分秒、纳秒来新建对象
        LocalDateTime localDateTime2 = LocalDateTime.of(2018, 11, 26, 13, 55, 36, 123);

        // 3年后的现在
        LocalDateTime dt1 = localDateTime.plusYears(3);
        // 3年前的现在
        LocalDateTime dt2 = localDateTime.minusYears(3);

        System.out.println("localDateTime  : " + localDateTime);
        System.out.println("localDateTime2 : " + localDateTime2);
        System.out.println("dt1            : " + dt1);
        System.out.println("dt2            : " + dt2);
    }
}
