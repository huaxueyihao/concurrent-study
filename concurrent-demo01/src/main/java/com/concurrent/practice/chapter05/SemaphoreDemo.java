package com.concurrent.practice.chapter05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    // 排队总人数 (请求总数)
    public static int clientTotal = 10;

    // 可同时受理业务的窗口数量（同时并发执行的线程数）
    public static int threadTotal = 2;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {

            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(1);
                    resolve(count);
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        System.out.println("countDownLatch await");
        countDownLatch.await();
        // 等待大家都完成了 ，一起结束
        System.out.println("countDownLatch await end");
        executorService.shutdown();


    }

    private static void resolve(int count) throws InterruptedException {
        System.out.println("服务号" + count + "受理业务中。。。。");
        Thread.sleep(2000);
    }


}
