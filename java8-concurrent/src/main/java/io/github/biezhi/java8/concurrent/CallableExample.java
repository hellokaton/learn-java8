package io.github.biezhi.java8.concurrent;

import java.util.concurrent.*;

/**
 * Callable Future
 *
 * @author biezhi
 * @date 2018/3/5
 */
public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.MINUTES.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future   = executor.submit(task);
        System.out.println("future done? " + future.isDone());
        Integer result = future.get();
        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);

    }
}
