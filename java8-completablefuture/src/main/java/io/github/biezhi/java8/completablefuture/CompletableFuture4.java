package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 扁平转换
 *
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture4 {

    public static void main(String[] args) {
        try {
            String s = CompletableFuture.supplyAsync(() -> 23333)
                    .thenCompose(t -> CompletableFuture.supplyAsync(() -> t + "ddd"))
                    .get();
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
