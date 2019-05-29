package com.concurrent.concurrent_four_state.third.tool.chapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CycliBarriedExample2 {

    public static void main(String[] args) throws InterruptedException {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        }.start();


        TimeUnit.SECONDS.sleep(4);
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());

        TimeUnit.SECONDS.sleep(2);

        cyclicBarrier.reset();

        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());




    }

}
