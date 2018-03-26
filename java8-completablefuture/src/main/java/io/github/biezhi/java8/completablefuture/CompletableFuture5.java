package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 消费结果
 *
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> 9999)
                .thenAccept(System.out::println).get();
    }
}
