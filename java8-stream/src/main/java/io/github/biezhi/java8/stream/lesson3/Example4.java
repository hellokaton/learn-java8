package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * 数据分区
 * <p>
 * Collectors.partitioningBy
 * <p>
 * 根据前后端将项目分为两组
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example4 {

    public static boolean isBackEnd(Project project){
        return "java".equalsIgnoreCase(project.getLanguage()) || "python".equalsIgnoreCase(project.getLanguage());
    }

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();

        Map<Boolean, List<Project>> collect = projects.stream()
                .collect(partitioningBy(Example4::isBackEnd));
        System.out.println(collect);
    }
}
