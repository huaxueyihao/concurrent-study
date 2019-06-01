package com.concurrent.concurrent_four_state.third.tool.chapter6;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ComBetweenThreads {

    private static int data = 0;

    private static boolean noUse = true;

    private final static Object MONITOR = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            for (;;){
                buildData();
            }
        }).start();

        new Thread(()->{
            for (;;){
                useData();
            }
        }).start();

    }

    private static void buildData() {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            data++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("p+>" + data);
            noUse = true;
            MONITOR.notifyAll();
        }
    }

    private static void useData() {
        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("C=>" + data);
            noUse = false;
            MONITOR.notifyAll();
        }
    }

}
