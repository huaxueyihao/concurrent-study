package com.concurrent.practice.chapter05;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {

                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }

                    } catch (InterruptedException e) {

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        System.out.println("start="+start);
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println("end="+end);
        return end - start;


    }

    public static void main(String[] args) {

        TestHarness testHarness = new TestHarness();
        try {
            long time = testHarness.timeTasks(5, new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "执行中。。。。");
                }
            });

            System.out.println(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
