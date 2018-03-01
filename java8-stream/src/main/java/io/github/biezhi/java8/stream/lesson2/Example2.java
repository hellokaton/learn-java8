package io.github.biezhi.java8.stream.lesson2;

import io.github.biezhi.java8.stream.Project;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 筛选元素
 *
 * filter 使用
 * distinct 使用
 * limit 使用
 * skip 使用
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Example2 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();

        List<Project> collect = projects.stream()
                .filter(project -> project.getStars() > 1000)
                .collect(Collectors.toList());

        // distinct
        Stream<Integer> numbers = Stream.of(1, 2, 3, 3, 2, 4);
        numbers.distinct().limit(3).forEach(n -> System.out.println(n));

        System.out.println("===================");
        Stream.of(1, 2, 3, 3, 2, 4).skip(4).forEach(n -> System.out.println(n));
    }

}
