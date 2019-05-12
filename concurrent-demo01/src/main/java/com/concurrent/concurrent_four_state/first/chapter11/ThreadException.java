package com.concurrent.concurrent_four_state.first.chapter11;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadException {


    private final static int A = 10;
    private final static int B = 0;


    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5_000L);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);
            System.out.println(thread);
        });

        t.start();

    }

}
