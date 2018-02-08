package io.github.biezhi.java8.growing.jdk8;

import java.util.Arrays;

/**
 * Lambda 表达式
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class Lambda {

    public static void main(String[] args) {
        Arrays.asList("a", "b", "d").forEach(System.out::println);

    }
}
