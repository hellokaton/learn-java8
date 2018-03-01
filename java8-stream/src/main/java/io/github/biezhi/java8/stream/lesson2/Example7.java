package io.github.biezhi.java8.stream.lesson2;

import io.github.biezhi.java8.stream.Project;

import java.util.List;
import java.util.OptionalInt;

/**
 * 数值流
 * <p>
 * IntStream、DoubleStream、LongStream
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Example7 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        OptionalInt max = projects.stream()
                .mapToInt(p -> p.getStars())
                .max();
        System.out.println(max.getAsInt());
    }

}