package io.github.biezhi.java8.defaultmethods;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class UseCalc {

    public static void main(String[] args) {

        Calculator calculator = new BasicCalculator();
        int sum = calculator.add(1, 2);
        System.out.println(sum);

        Calculator cal = Calculator.getInstance();
        int difference = cal.subtract(3, 2);
        System.out.println(difference);

        System.out.println(cal.mod(3, 2));
    }
}
