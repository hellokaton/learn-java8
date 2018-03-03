package io.github.biezhi.java8.stream.lesson3;

import java.util.Arrays;

/**
 * 并行流
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example6 {

    public static void main(String[] args) {
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
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));
    }
}