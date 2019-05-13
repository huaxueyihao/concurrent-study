package com.concurrent.concurrent_four_state.first.chapter12;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadGroupAPI {

    public static void main(String[] args) {

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t2.start();

        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());

        t2.checkAccess();




    }

}
