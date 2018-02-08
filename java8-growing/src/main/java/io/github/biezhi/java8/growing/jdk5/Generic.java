package io.github.biezhi.java8.growing.jdk5;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛型
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class Generic<T> {

    public T getById(Integer id){
        return null;
    }

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();

        Generic<Long> generic = new Generic<>();

    }

}
