package io.github.biezhi.java8.growing.jdk8;

/**
 * 默认方法
 *
 * @author biezhi
 * @date 2018/2/8
 */
@FunctionalInterface
public interface FunctionalDefaultMethods {

    void method();

    default void defaultMethod() {
    }
}