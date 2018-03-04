package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 1. 计数
 * <p>
 * Collectors.counting
 * count
 * <p>
 * 2. 最值
 * Collectors.maxBy
 * <p>
 * 3. 求和
 * Collectors.summingInt
 * <p>
 * 4. 求平均值
 * Collectors.averagingInt
 * <p>
 * 5. 连接字符串
 * Collectors.joining
 * <p>
 * 6. 一般归约
 * Collectors.reducing
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example1 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        Double collect = projects.stream()
                .collect(averagingInt(Project::getStars));
        System.out.println(collect);

        System.out.println(Stream.of("Hello", "Java8")
                .collect(joining(",")));

        Integer collect1 = projects.stream()
                .collect(reducing(0, Project::getStars, (x, y) -> x + y));
        System.out.println(collect1);

        Optional<Integer> collect2 = projects.stream()
                .map(Project::getStars)
                .collect(reducing((x, y) -> x + y));
        System.out.println(collect2);
    }
}
