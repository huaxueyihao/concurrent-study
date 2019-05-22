package com.concurrent.concurrent_four_state.third.atomic.chapter1;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class JITTest {

    private volatile static boolean init = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(){
            @Override
            public void run() {
                while (!init){

                }
            }
        }.start();

        Thread.sleep(1000);

        new Thread(){
            @Override
            public void run() {
                init = true;
                System.out.println("Set init to true");
            }
        }.start();

    }

}
