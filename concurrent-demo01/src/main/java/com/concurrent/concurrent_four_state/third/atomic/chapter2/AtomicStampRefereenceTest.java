package com.concurrent.concurrent_four_state.third.atomic.chapter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicStampRefereenceTest {


    private static AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    boolean b1 = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println("b1=" + b1);
                    boolean b2 = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                    System.out.println("b2=" + b2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    int stamp = atomicRef.getStamp();
                    System.out.println("Before sleep:stamp=" + stamp);

                    TimeUnit.SECONDS.sleep(1);
                    boolean b = atomicRef.compareAndSet(100, 101, stamp, stamp + 1);
                    System.out.println("b3=" + b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();;
        t2.start();
        t1.join();
        t2.join();


    }


}
