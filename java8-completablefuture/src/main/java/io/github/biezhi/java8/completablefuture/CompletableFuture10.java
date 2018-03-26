package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture10 {

    private static CompletableFuture<Integer> m1(){
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2333;
        });
    }
    private static CompletableFuture<Integer> m2(){
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 8877;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture.anyOf(m1(), m2())
        .thenRun(() -> {
            System.out.println(System.currentTimeMillis() - start);
        }).get()
        ;
    }
}
