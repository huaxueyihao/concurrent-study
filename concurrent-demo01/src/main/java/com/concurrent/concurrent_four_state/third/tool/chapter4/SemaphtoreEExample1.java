package com.concurrent.concurrent_four_state.third.tool.chapter4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SemaphtoreEExample1 {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(1);

        final SemaphoreLock semaphoreLock = new SemaphoreLock();


        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        semaphoreLock.lock();
                        System.out.println(Thread.currentThread().getName()+" get Lock");
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphoreLock.unlock();
                    }
                    System.out.println(Thread.currentThread().getName()+" release Lock");
                }
            }.start();
        }




    }

    static class SemaphoreLock {

        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }

    }

    private synchronized static void m(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
