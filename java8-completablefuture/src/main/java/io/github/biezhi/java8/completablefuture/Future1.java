package io.github.biezhi.java8.completablefuture;

import java.util.concurrent.*;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class Future1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> submit = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 100;
        });

//        try {
//            Integer result = submit.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        while(!submit.isDone()){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer result = submit.get();
            System.out.println(result);
    }
}
