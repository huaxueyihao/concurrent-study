package com.concurrent.concurrent_four_state.first.chapter12;

import java.util.Arrays;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadGroupCreate {

    public static void main(String[] args) {

        // use the name

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
//                        System.out.println(getThreadGroup().getName());
//                        System.out.println(getThreadGroup().getParent());
//                        System.out.println(getThreadGroup().getParent().activeCount());
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        t1.start();

        ThreadGroup tg2 = new ThreadGroup("TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                System.out.println(tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
//                System.out.println(tg1.e);
            }
        };
        t2.start();
//        System.out.println(tg2.getName());
//        System.out.println(tg2.getParent());
//        System.out.println(tg2.getParent().activeCount());

//        System.out.println(t1.getThreadGroup().getName());


//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());


    }

}
