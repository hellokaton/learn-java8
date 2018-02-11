package io.github.biezhi.java8.stream.lesson1;

import io.github.biezhi.java8.stream.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class Java8 {

    public static void main(String[] args) {
        List<Project> result = new ArrayList<>();

        List<Project> projects = new ArrayList<>();
        List<String> names = projects.stream()
                .filter(p -> p.getStars() > 1000)
                .map(Project::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
    }

}
