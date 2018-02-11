package io.github.biezhi.java8.defaultmethods.conflict;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public interface A {

    default void sayHello(){
        System.out.println("你好，我是 Java 8");
    }

}
