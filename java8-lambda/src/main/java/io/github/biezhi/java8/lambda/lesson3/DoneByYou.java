package io.github.biezhi.java8.lambda.lesson3;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * 由你完成的
 * <p>
 * 下列Lambda表达式的等效方法引用是什么
 *
 * @author biezhi
 * @date 2018/2/10
 */
public class DoneByYou {

    public static void main(String[] args) {
        Function<String, Integer>         stringToInteger = (String s) -> Integer.parseInt(s);
        BiPredicate<List<String>, String> contains        = (list, element) -> list.contains(element);

    }
}
