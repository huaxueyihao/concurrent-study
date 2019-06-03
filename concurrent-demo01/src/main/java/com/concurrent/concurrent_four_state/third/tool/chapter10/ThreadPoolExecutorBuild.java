package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadPoolExecutorBuild {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;

        while (true) {
            if (activeCount != executorService.getActiveCount() || queueSize != executorService.getQueue().size()) {
                System.out.println(executorService.getActiveCount());
                System.out.println(executorService.getCorePoolSize());
                System.out.println(executorService.getQueue().size());
                System.out.println(executorService.getMaximumPoolSize());
                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
                System.out.println("==============================");

            }
        }


    }

    /**
     * Testing point:
     * <p>
     * 1.coreSize=1,MaxSize=2 blockingQueue is empty what happen then submit 3 task ?
     * 2.coreSize=1,MaxSize=2 blockingQUeue size = 5 what happen when submit 7 task ?
     * 3.coreSize=1,MaxSize=2 blockingQUeue size = 5 what happen when submit 8 task ?
     * </p>
     */
    private static ExecutorService buildThreadPoolExecutor() {

        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());
        System.out.println(" === the poolExecutor create done");
        executorService.execute(() -> {
            System.out.println("**" + Thread.currentThread().getName() + "**");
            ToolUtil.sleep(100,TimeUnit.SECONDS);
        });
        executorService.execute(() -> {
            System.out.println("**" + Thread.currentThread().getName() + "**");
            ToolUtil.sleep(10,TimeUnit.SECONDS);
        });
        executorService.execute(() -> {
            System.out.println("**" + Thread.currentThread().getName() + "**");
            ToolUtil.sleep(10,TimeUnit.SECONDS);
        });

        return executorService;

    }


}
