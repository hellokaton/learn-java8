package io.github.biezhi.java8.lambda.lesson3;

import io.github.biezhi.java8.lambda.lesson1.Project;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * 方法引用
 * <p>
 * 1. 指向静态方法的方法引用
 * 2. 指向现有对象的实例方法的方法引用
 *
 * @author biezhi
 * @date 2018/2/10
 */
public class MethodReference {

    public static List<Integer> findNumbers(List<Integer> numbers, Predicate<Integer> filter) {
        List<Integer> numbersFound = numbers
                .stream()
                .filter(filter)
                .collect(toList());

        return numbersFound;
    }

    public static boolean multipleOf3(Integer number) {
        return (number % 3) == 0;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 6, 8, 9, 12, 14, 15);

        List<Integer> multiplesOf3 = findNumbers(numbers, MethodReference::multipleOf3);
        System.out.println(multiplesOf3.contains(3));

        Project project = Project.builder().name("Blade").build();
        Arrays.asList(project).stream()
                .map(Project::getName)
                .count();

    }

}
