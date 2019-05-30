package com.concurrent.concurrent_four_state.third.tool.chapter4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SemaphoreExample2 {


    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " in ");
                    try {
                        semaphore.acquire(2);
                        System.out.println(Thread.currentThread().getName() + " Get the semaphore ");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    } finally {
                        semaphore.release(2);
                    }
                    System.out.println(Thread.currentThread().getName() + " out ");
                }
            }.start();

        }

        while (true) {
            System.out.println("AP-> " + semaphore.availablePermits());
            System.out.println("QL-> " + semaphore.getQueueLength());
            System.out.println("=================================");
            TimeUnit.SECONDS.sleep(1);
        }

    }


}
