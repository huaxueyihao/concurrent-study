package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class FutureExample1 {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        testGet();
        testGetWithTimeout();

    }

    /**
     * {@link Future#get()}
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            ToolUtil.sleep(10, TimeUnit.SECONDS);
            return 10;
        });
        // =========================
        System.out.println("===== I will be printed quikly.=====");
        // =========================

        Thread callerThread = Thread.currentThread();

        new Thread(() -> {
            ToolUtil.sleep(3, TimeUnit.SECONDS);
            callerThread.interrupt();
        }).start();

        Integer result = future.get();
        System.out.println("=============" + result + "===========");

    }



    private static void testGetWithTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            ToolUtil.sleep(10, TimeUnit.SECONDS);
            System.out.println("=============");
            return 10;
        });
        // =========================
        System.out.println("===== I will be printed quikly.=====");
        // =========================

        Integer result = future.get(5,TimeUnit.SECONDS);
        System.out.println("=============" + result + "===========");

    }


}
