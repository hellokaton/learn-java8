package io.github.biezhi.java8.stream.lesson2;

import io.github.biezhi.java8.stream.Project;

import java.util.Arrays;
import java.util.List;

/**
 * 归约（reduce）
 * <p>
 * 1.元素求和
 * 2.
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Example6 {

    public static void main(String[] args) {
        List<Project> projects = Project.buildData();
        List<Integer> numbers  = Arrays.asList(2, 4, 5, 6);

        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }

        System.out.println(sum);

        Integer reduce = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
    }

}
