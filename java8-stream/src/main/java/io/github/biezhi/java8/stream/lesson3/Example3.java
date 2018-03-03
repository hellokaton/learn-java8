package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 使用分组区分前端和后端项目
 * <p>
 * Collectors.groupingBy
 * <p>
 * 然后根据作者姓名再做一次分组
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example3 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        System.out.println(projects.stream().collect(
                Collectors.groupingBy(p -> {
                    if (p.getLanguage().equalsIgnoreCase("java")
                            || p.getLanguage().equalsIgnoreCase("python")) {
                        return "后端";
                    }
                    return "前端";
                })));
    }
}
