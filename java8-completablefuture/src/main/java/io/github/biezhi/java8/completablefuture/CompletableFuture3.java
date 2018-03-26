package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * 结果转换
 *
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture3 {

    public static void main(String[] args) {
        try {
            String result = CompletableFuture.supplyAsync(() -> 2333)
                    .thenApply(String::valueOf).get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
