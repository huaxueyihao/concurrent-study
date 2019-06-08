package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CompletionServiceExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        futureDefect2();
    }


    private static void futureDefect1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            ToolUtil.sleep(100, TimeUnit.SECONDS);
            return 100;
        });

        System.out.println("==================");
        future.get();
    }

    private static void futureDefect2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
            ToolUtil.sleep(10, TimeUnit.SECONDS);
            return 10;
        }, () -> {
            ToolUtil.sleep(20, TimeUnit.SECONDS);
            return 20;
        });

        List<Future<Integer>> futures = executorService.invokeAll(callableList);
        Integer v1 = futures.get(1).get();
        System.out.println(v1);
        Integer v2 = futures.get(0).get();
        System.out.println(v2);


    }
}
