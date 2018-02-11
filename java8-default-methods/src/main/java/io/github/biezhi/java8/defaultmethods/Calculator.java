package io.github.biezhi.java8.defaultmethods;

/**
 * 计算器接口
 *
 * @author biezhi
 * @date 2018/2/11
 */
public interface Calculator {

    int add(int first, int second);

    int subtract(int first, int second);

    int multiply(int first, int second);

    int divide(int number, int divisor);

    default int mod(int first, int second){
        return first % second;
    }

    static Calculator getInstance(){
        return new BasicCalculator();
    }
}
