package io.github.biezhi.java8.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ConcurrentMap
 * <p>
 * forEach
 * putIfAbsent
 * getOrDefault
 * replaceAll
 * compute
 * merge
 * search
 * reduce
 *
 * @author biezhi
 * @date 2018/3/5
 */
public class ConcurrentMapExample {

    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));

    }
}
