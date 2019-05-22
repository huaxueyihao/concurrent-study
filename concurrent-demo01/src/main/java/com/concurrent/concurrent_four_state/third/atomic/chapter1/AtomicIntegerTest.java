package com.concurrent.concurrent_four_state.third.atomic.chapter1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicIntegerTest {


    /**
     * 1.内存可见性
     * 2.内存屏障 顺序性
     * 3.no atomic
     */
    private static volatile int value;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " " + tmp);
                    value += 1;
                    x++;
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " " + tmp);
                    value += 1;
                    x++;
                }

            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " " + tmp);
                    value += 1;
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
