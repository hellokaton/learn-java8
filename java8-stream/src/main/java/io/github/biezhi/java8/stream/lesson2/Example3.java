package io.github.biezhi.java8.stream.lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 映射
 * <p>
 * map 使用
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Example3 {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");

        words.stream()
                .map(word -> word.length())
                .collect(Collectors.toList())
        .forEach(i -> System.out.println(i));
    }

}
