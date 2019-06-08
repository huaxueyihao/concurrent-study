package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class FutureExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        testCancel();
    }

    private static void testIsDone() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
//            ToolUtil.sleep(10, TimeUnit.SECONDS);
//            System.out.println("=============");
            throw new RuntimeException();
//            return 10;
        });
        try {
            Integer result = future.get();
            System.out.println(result + " is done " + future.isDone());
        } catch (Exception e) {
            System.out.println(" is Done " + future.isDone());
        }


    }


    private static void testCancel() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            ToolUtil.sleep(10,TimeUnit.SECONDS);
            return 10;
        });
//        System.out.println(future.get());
        ToolUtil.sleep(10,TimeUnit.MILLISECONDS);
        System.out.println(future.cancel(true));


    }

}
