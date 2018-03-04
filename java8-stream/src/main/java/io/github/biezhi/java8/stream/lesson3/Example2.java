package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 将结果收集到 Map 中
 * <p>
 * Collectors.toMap
 * Function.identity()
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example2 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();

        Map<String, Integer> collect = projects.stream()
                .collect(toMap(Project::getName, Project::getStars));
        System.out.println(collect);

        Map<String, Project> collect1 = projects.stream()
                .collect(toMap(Project::getName, Function.identity()));
        System.out.println(collect1);
    }
}
