package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.List;

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
    }
}
