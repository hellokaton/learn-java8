package io.github.biezhi.java8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 同步
 *
 * @author biezhi
 * @date 2018/3/5
 */
public class SynchronizeExample {

    int count = 0;

    private void increment() {
        count = count + 1;
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::increment));
        stop(executor);
        System.out.println(count);  // 9965
    }

    public static void main(String[] args) {
        new SynchronizeExample().start();
    }

    public void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

}
