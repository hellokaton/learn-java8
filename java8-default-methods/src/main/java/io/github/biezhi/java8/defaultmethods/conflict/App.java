package io.github.biezhi.java8.defaultmethods.conflict;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class App implements A {

    @Override
    public void sayHello(){
        System.out.println("你好，我是 APP");
    }

    public static void main(String[] args) {
        new App().sayHello();
    }

}
