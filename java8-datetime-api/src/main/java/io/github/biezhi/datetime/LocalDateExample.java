package io.github.biezhi.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * LocalDate 示例
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class LocalDateExample {

    public static void main(String[] args) {

        // 创建一个LocalDate实例
        LocalDate localDate = LocalDate.now();

        // 使用年月日信息构造出LocalDate对象
        LocalDate localDate2 = LocalDate.of(2018, 3, 3);

        int       year       = localDate.getYear();
        Month     month      = localDate.getMonth();
        int       dayOfMonth = localDate.getDayOfMonth();
        int       dayOfYear  = localDate.getDayOfYear();
        DayOfWeek dayOfWeek  = localDate.getDayOfWeek();

        System.out.println("year       : " + year);
        System.out.println("month      : " + month.getValue());
        System.out.println("dayOfMonth : " + dayOfMonth);
        System.out.println("dayOfYear  : " + dayOfYear);
        System.out.println("dayOfWeek  : " + dayOfWeek.getValue());

        // 3年后
        LocalDate d1 = localDate2.plusYears(3);
        // 3年前
        LocalDate d2 = localDate2.minusYears(3);
        System.out.println("plusYears  : " + d1);
        System.out.println("minusYears : " + d2);
    }
}
