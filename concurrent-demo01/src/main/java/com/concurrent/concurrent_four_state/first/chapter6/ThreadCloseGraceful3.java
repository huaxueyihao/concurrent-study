package com.concurrent.concurrent_four_state.first.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadCloseGraceful3 {

    private static class Worker extends Thread{


        @Override
        public void run() {

            // connection
        }

    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();


    }






}
