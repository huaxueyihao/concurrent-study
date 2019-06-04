package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.List;
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
public class ThreadPoolExecutorTask {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 20).boxed().forEach(i -> {
            executorService.execute(() -> {
                ToolUtil.sleep(10, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done");
            });
        });

//        executorService.shutdown();
//        executorService.awaitTermination(1,TimeUnit.HOURS);
        try {
            List<Runnable> runnables = executorService.shutdownNow();
            System.out.println(runnables);
        } catch (Exception e) {

        }

        System.out.println("==============================");
    }

}
