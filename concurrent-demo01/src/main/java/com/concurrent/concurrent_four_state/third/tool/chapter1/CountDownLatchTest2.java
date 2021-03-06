package com.concurrent.concurrent_four_state.third.tool.chapter1;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CountDownLatchTest2 {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(){
            @Override
            public void run() {
                System.out.println("Do some initial working.");
                try {
                    Thread.sleep(1000);
                    latch.await();
                    System.out.println("Do other working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                System.out.println("asyn prepare for some data.");
                try {
                    Thread.sleep(2000);
                    System.out.println("data prepare for done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        }.start();

        Thread.currentThread().join();

    }



}
