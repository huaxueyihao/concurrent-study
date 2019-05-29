package com.concurrent.concurrent_four_state.third.tool.chapter2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CyclicBarriedExample3 {

    static class MyCountDownLatch extends CountDownLatch{

        private final Runnable runnable;

        public MyCountDownLatch(int count,Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        public void countDown(){
            super.countDown();
            if(getCount() == 0){
                this.runnable.run();
            }
        }


    }


    public static void main(String[] args) {

        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all of finish done ");
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+" finished");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+" finished");
            }
        }.start();
    }
}
