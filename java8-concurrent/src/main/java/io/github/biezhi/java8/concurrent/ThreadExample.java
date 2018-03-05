package io.github.biezhi.java8.concurrent;

/**
 * 线程
 *
 * @author biezhi
 * @date 2018/3/5
 */
public class ThreadExample {

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };
        task.run();
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Done!");
    }

}
