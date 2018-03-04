package io.github.biezhi.java8.stream.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 并行流
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example6 {

    public static void main(String[] args) {
//        int          max    = 1000000;
//        List<String> values = new ArrayList<>(max);
//        for (int i = 0; i < max; i++) {
//            UUID uuid = UUID.randomUUID();
//            values.add(uuid.toString());
//        }
//
//        long t0 = System.nanoTime();
//        long count = values.parallelStream().sorted().count();
//        System.out.println(count);
//        long t1 = System.nanoTime();
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("sequential sort took: %d ms", millis));
//
//        List<Integer> list = Arrays.asList(1, 3, 4, 2, 9);
//        list.stream()
//                .parallel();

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

    }
}