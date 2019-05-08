package com.concurrent.concurrent_four_state.first.chapter6;


/**
 * @Description: 开关的方式 终止线程
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadCloseGraceful {

    private static class Worker extends Thread{

        private volatile  boolean start = true;

        @Override
        public void run() {
            while (start){
                System.out.println("start="+start);
            }
        }

        public void shutdown(){
            this.start = false;
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
        worker.shutdown();


    }




}
