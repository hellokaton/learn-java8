package io.github.biezhi.java8.stream.lesson3;

import io.github.biezhi.java8.stream.Project;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * 根据作者名进行分组
 * <p>
 * Collectors.groupingBy
 * <p>
 * 然后根据编程语言类型做前后端分组
 *
 * @author biezhi
 * @date 2018/3/2
 */
public class Example3 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        Map<String, List<Project>> collect = projects.stream()
                .collect(groupingBy(Project::getAuthor));
        System.out.println(collect);

        Map<String, Map<String, Long>> collect1 = projects.stream()
                .collect(groupingBy(Project::getAuthor,
                        groupingBy(p -> {
                            if ("java".equalsIgnoreCase(p.getLanguage()) ||
                                    "python".equalsIgnoreCase(p.getLanguage())) {
                                return "后端";
                            }
                            return "前端";
                        }, counting())
                ));
        System.out.println(collect1);
    }
}
