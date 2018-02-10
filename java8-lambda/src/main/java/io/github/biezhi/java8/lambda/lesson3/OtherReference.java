package io.github.biezhi.java8.lambda.lesson3;

import java.util.function.Function;

/**
 * 数组引用
 *
 * @author biezhi
 * @date 2018/2/10
 */
public class OtherReference {

    public static void main(String[] args) {
        Function<Integer, String[]> fun  = x -> new String[x];
        String[]                    strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun1 = String[]::new;
        strs = fun1.apply(20);
        System.out.println(strs.length);
    }

}
