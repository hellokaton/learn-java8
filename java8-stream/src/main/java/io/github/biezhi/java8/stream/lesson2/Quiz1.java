package io.github.biezhi.java8.stream.lesson2;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * 3. 斐波纳契元组序列
 * <p>
 * 斐波纳契数列是著名的经典编程练习。
 * 下面这个数列就是斐波纳契数列的一部分：
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55…数列中开始的两个数字是 0 和 1，后续的每个数字都是前两个数字之和。
 * <p>
 * 斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：
 * (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
 * 你的任务是用iterate方法生成斐波纳契元组序列中的前20个元素。
 *
 * @author biezhi
 * @date 2018/2/12
 */
public class Quiz1 {

    @AllArgsConstructor
    static class Tuple{
        int first;
        int second;
    }

    public static void main(String[] args) {
        // tuple = (0, 1)
        // next [0] = prev tuple [1]
        // next [1] = prev (tuple [0] + tuple[1])
        Stream.iterate(new Tuple(0, 1), tuple -> new Tuple(tuple.second, tuple.first + tuple.second))
                .limit(20)
                .forEach(tuple -> System.out.println("("+ tuple.first +","+ tuple.second +")"));
    }

}
