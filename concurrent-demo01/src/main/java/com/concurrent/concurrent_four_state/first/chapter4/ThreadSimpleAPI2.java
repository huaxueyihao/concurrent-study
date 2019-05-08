package com.concurrent.concurrent_four_state.first.chapter4;

import java.util.Optional;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadSimpleAPI2 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }

        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }

        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }

        });

        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
