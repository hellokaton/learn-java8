package io.github.biezhi.java8.lambda.lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * lambda 中有异常
 * <p>
 * 任何函数式接口都不允许抛出受检异常
 * <p>
 * sf上这个问题的一些讨论：https://stackoverflow.com/questions/18198176/java-8-lambda-function-that-throws-exception
 *
 * @author biezhi
 * @date 2018/2/10
 */
public class LambdaException {

    public static void main(String[] args) {
        Function<BufferedReader, String> f = (BufferedReader b) -> {
            try {
                return b.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
