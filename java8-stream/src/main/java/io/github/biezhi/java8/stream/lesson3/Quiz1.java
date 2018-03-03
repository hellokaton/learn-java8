package io.github.biezhi.java8.stream.lesson3;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 *
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Quiz1 {

    @AllArgsConstructor
    static class Tuple{
        int first;
        int second;
    }

    public static void main(String[] args) {
        // tuple = (0, 1)
        // next [0] = prev tuple [1]
        // next [1] = prev (tuple [0] + tuple[1])
        Stream.iterate(new Tuple(0, 1), tuple -> new Tuple(tuple.second, tuple.first + tuple.second))
                .limit(20)
                .forEach(tuple -> System.out.println("("+ tuple.first +","+ tuple.second +")"));
    }

}
