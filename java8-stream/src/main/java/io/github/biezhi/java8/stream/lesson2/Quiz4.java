package io.github.biezhi.java8.stream.lesson2;

import io.github.biezhi.java8.stream.Project;

import java.util.List;

/**
 * 4. 请使用reduce计算 biezhi 的项目有多少 star
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Quiz4 {

    public static void main(String[] args) {

        List<Project> projects = Project.buildData();
        Integer biezhi = projects.stream()
                .filter(p -> p.getAuthor().equals("biezhi"))
                .map(p -> p.getStars())
                .reduce(0, Integer::sum);
        System.out.println(biezhi);
    }

}