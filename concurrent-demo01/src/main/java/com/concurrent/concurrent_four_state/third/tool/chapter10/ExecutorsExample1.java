package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutorsExample1 {

    public static void main(String[] args) {

        useCachedThreadPool();

    }

    private static void useFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }

    private static void useCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        executorService.execute(() -> System.out.println("================="));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());


        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            ToolUtil.sleep(100, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");

        }));

        ToolUtil.sleep(1, TimeUnit.SECONDS);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

    }

}
