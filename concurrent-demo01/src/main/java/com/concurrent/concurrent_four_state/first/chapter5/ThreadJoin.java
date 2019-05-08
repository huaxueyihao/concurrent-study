package com.concurrent.concurrent_four_state.first.chapter5;

import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadJoin {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println((Thread.currentThread().getName() + "->" + i)));
        });

        t1.start();
        t1.join();


        IntStream.range(1, 1000).forEach(i -> System.out.println((Thread.currentThread().getName() + "->" + i)));


    }


}
