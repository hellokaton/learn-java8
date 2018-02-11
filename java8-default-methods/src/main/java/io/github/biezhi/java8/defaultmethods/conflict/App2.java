package io.github.biezhi.java8.defaultmethods.conflict;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class App2 implements A, B, C {

    public static void main(String[] args) {
        new App2().sayHello();
    }

}
