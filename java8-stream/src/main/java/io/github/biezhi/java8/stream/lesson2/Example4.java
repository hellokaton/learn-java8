package io.github.biezhi.java8.stream.lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 扁平流 flatMap
 * <p>
 * 列出List中各不相同的单词
 * <p>
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Example4 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("I am a boy", "I love the girl", "But the girl loves another girl");

        list.stream()
                .map(word -> word.split(" "))   // Stream<String>
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

    }

}
