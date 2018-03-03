package io.github.biezhi.datetime;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * Clock 示例
 *
 * @author biezhi
 * @date 2018/3/3
 */
public class ClockExample {

    public static void main(String[] args) {
        Clock   clock      = Clock.systemDefaultZone();
        long    millis     = clock.millis();
        Instant instant    = clock.instant();
        Date    legacyDate = Date.from(instant);   // legacy java.util.Date
        System.out.println(millis);
        System.out.println(legacyDate);
    }
}
