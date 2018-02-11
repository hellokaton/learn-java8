package io.github.biezhi.java8.defaultmethods.conflict;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class App3 implements A, B {

    @Override
    public void sayHello() {
        System.out.println("大家好，我系古天乐。探晚懒月，里没有晚过的传奇。" +
                "点一下，晚一连，撞贝不花一份钱。机要晚过了传骑，里就系我的凶第。");
    }

    public static void main(String[] args) {
        new App3().sayHello();
    }

}
