package io.github.biezhi.java8.growing.jdk5;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛型
 */
public class Generic<T> {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();

        Generic<Long> generic = new Generic<>();

    }

}
