package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture8 {

    public static void main(String[] args) {
        try {
            String s = CompletableFuture.supplyAsync(() -> 23333)
                    .thenCombine(CompletableFuture.supplyAsync( () -> "8898" ), (a, b) -> {
                        System.out.println("a =" + a);
                        System.out.println("b =" + b);
                        return a + b;
                    })
                    .get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
