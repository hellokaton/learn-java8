package io.github.biezhi.java8.lambda.lesson2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

/**
 * 函数式接口示例
 *
 * @author biezhi
 * @date 2018/2/10
 */
public class FunctionalDemo {

    /**
     * 断言
     */
    public void predicate() {
        Predicate<String> namesStartingWithS = name -> name.startsWith("s");
        boolean           hello              = namesStartingWithS.test("Hello");
        // false
    }

    /**
     * 消费数据
     */
    public void consumer() {
        Consumer<String> messageConsumer = message -> System.out.println(message);
        messageConsumer.accept("Learn Java8");  // Learn Java8"

    }

    /**
     * 转换
     */
    public void function() {
        Function<String, String> toUpperCase = name -> name.toUpperCase();
        toUpperCase.apply("Java"); // Java
    }

    /**
     * 提供数据
     */
    public void supplier() {
        Supplier<String> uuidGenerator = () -> UUID.randomUUID().toString();
        System.out.println(uuidGenerator.get());

    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 300; i < 400; i++) {
            list.add(i);
        }

        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        evenNumbers.test(1000);

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        oddNumbers.test(1000);

        Function<Integer, Integer> add1   = x -> x + 1;
        Function<String, String>   concat = x -> x + 1;

        Integer two    = add1.apply(1); //yields 2
        String  answer = concat.apply("0 + 1 = "); // "0 + 1 = 1"

        BinaryOperator<Integer> sum = (a, b) -> a + b;
        Integer                 res = sum.apply(1, 2); // 3

        BinaryOperator<Function<Integer, Integer>> compose = (f, g) -> x -> g.apply(f.apply(x));

        UnaryOperator<Integer>                    add2    = n -> n + 1;
        UnaryOperator<String>                     concat1 = s -> s + 1;
        Function<Integer, UnaryOperator<Integer>> sum2    = x -> y -> x + y;
        UnaryOperator<Integer>                    sum10   = sum2.apply(10);

    }
}
