package com.concurrent.concurrent_four_state.third.tool.chapter10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadPoolExecutorLongTimeTask {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());


        IntStream.range(0,10).boxed().forEach(i->{
            executorService.submit(()->{
               while (true){

               }
            });
        });

        executorService.shutdown();
        executorService.awaitTermination(5,TimeUnit.SECONDS);
        System.out.println("================start the sequence working=================");
    }

}
