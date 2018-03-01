package io.github.biezhi.java8.stream.lesson2;

import io.github.biezhi.java8.stream.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. 你将如何利用流来筛选前两个Java项目呢？
 * <p>
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Quiz2 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();

        System.out.println(projects.stream().map(Project::getName).limit(2).collect(Collectors.toList()));
    }

}
