package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture9 {

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
        m1().acceptEither(m2(), t -> {
            System.out.println("t = " + t);
        }).get();
    }
}
