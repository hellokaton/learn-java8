package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 处理计算结果
 *
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture2 {

    public static void main(String[] args) {
        CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始执行运算");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = 1/0;
            System.out.println("执行结束");
            return 2333;
        });

        try {
            Integer result = uCompletableFuture.whenComplete((a, b) -> {
                System.out.println("Result: " + a);
                System.out.println("Exception: " + b);
            }).exceptionally(e -> {
                System.out.println(e.getMessage());
                return 666;
            }).get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
