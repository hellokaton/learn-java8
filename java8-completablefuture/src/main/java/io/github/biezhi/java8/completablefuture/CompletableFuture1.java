package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * 创建 CompletableFuture
 *
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture1 {

    public static void main(String[] args) {
        CompletableFuture<Void> helloFuture = CompletableFuture.runAsync(() -> System.out.println("hello future"));

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 2333);

    }
}
