package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.*;

/**
 * 转换类型
 * <p>
 * Collectors.toCollection
 * <p>
 * Collectors.collectingAndThen
 * <p>
 * Collectors.maxBy
 * <p>
 * Collectors.minBy
 * <p>
 * 按照作者名称筛选出每组star最高的项目
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example5 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();

        Collection<Project> collect = projects.stream()
                .collect(toCollection(CopyOnWriteArrayList::new));
        System.out.println(collect);

        Map<String, Project> collect1 = projects.stream()
                .collect(groupingBy(Project::getAuthor, collectingAndThen(
                        maxBy(Comparator.comparingInt(Project::getStars)),
                        Optional::get
                )));
        System.out.println(collect1);
    }
}
