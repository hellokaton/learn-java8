package io.github.biezhi.java8.stream.lesson1;

import io.github.biezhi.java8.stream.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class Java7 {

    public static void main(String[] args) {
        List<Project> result = new ArrayList<>();

        List<Project> projects = new ArrayList<>();
        for (Project project : projects) {
            if(project.getStars() > 1000){
                result.add(project);
            }
        }
        Collections.sort(projects, new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getStars().compareTo(o2.getStars());
            }
        });

        List<String> names = new ArrayList<>();

        for (Project project : projects) {
            names.add(project.getName());
        }
    }

}
