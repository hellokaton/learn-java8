package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> 9999)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> "7878"), (a, b) -> {
                    System.out.println("a = " + a);
                    System.out.println("b = " + b);
                }).get();
    }
}
