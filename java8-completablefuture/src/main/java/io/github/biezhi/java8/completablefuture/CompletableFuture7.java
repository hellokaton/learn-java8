package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture7 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("开始执行了");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 9999;
        }).thenRun(() -> {
            System.out.println("执行结束了");
        }).get();
    }
}
