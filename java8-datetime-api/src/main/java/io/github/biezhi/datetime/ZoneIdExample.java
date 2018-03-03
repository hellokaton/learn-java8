package io.github.biezhi.datetime;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * ZoneId 示例
 *
 * @author biezhi
 * @date 2018/3/3
 */
public class ZoneIdExample {

    public static void main(String[] args) {

        // 获取系统默认时区
        ZoneId defaultZoneId  = ZoneId.systemDefault();
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");

        // TimeZone 转换为 ZoneId
        ZoneId oldToNewZoneId = TimeZone.getDefault().toZoneId();

        System.out.println(defaultZoneId);
        System.out.println(shanghaiZoneId);
        System.out.println(oldToNewZoneId);

        System.out.println(ZoneId.getAvailableZoneIds());
    }
}
