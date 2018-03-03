package io.github.biezhi.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Duration 示例
 *
 * @author biezhi
 * @date 2018/3/3
 */
public class DurationExample {

    public static void main(String[] args) throws InterruptedException {

        // 创建Duration实例
        Instant first = Instant.now();
        Thread.sleep(3000);
        Instant  second   = Instant.now();
        Duration duration = Duration.between(first, second);

        // 访问Duration的时间
        long seconds = duration.getSeconds();

        System.out.println("相差 : " + seconds + " 秒");

        LocalDateTime from      = LocalDateTime.now();
        LocalDateTime to        = from.plusDays(5);
        Duration      duration2 = Duration.between(from, to);

        System.out.println("从 from 到 to 相差 : " + duration2.toDays() + " 天");
    }
}
