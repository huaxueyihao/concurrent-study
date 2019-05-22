package com.concurrent.concurrent_four_state.third.atomic.chapter1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicIntegerTest2 {


    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger value = new AtomicInteger();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + " " + v);
                    x++;
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + " " + v);
                    x++;
                }

            }
        };


        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + " " + v);
                    x++;
                }

            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(set.size());
    }


}
