package io.github.biezhi.java8.stream.lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 创建流
 * <p>
 * Stream.of
 * Arrays.stream
 * collection.stream
 * Files.lines
 * Stream.iterate
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Example1 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
        Stream<String> stream = list.stream();

        Stream<String> stringStream = Arrays.stream(new String[]{"hello", "world"});

        Stream<String> stream1 = Stream.of("hello", "world");
    }

}
