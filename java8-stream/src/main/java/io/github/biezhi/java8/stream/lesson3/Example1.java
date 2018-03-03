package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example1 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        projects.stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
        projects.stream().collect(Collectors.toSet());
    }
}
