package io.github.biezhi.java8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 原子变量
 * <p>
 * AtomicInteger
 * LongAdder
 * LongAccumulator
 *
 * @author biezhi
 * @date 2018/3/5
 */
public class AtomicExample {

    public static void main(String[] args) {
        AtomicInteger   atomicInt = new AtomicInteger(0);
        ExecutorService executor  = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));
        stop(executor);
        System.out.println(atomicInt.get());
    }

    public static void stop(ExecutorService executor) {
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
