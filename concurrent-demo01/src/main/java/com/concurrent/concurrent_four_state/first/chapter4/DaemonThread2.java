package com.concurrent.concurrent_four_state.first.chapter4;

/**
 * @Description: 守护线程做心跳检测
 * @Author: huaxueyihao
 * @Version:
 **/
public class DaemonThread2 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            Thread innerThread = new Thread(() -> {
                try {
                    while (true){
                        System.out.println("Do something");
                        Thread.sleep(100_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.setDaemon(true);
            innerThread.start();

            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t.start();


    }

}
