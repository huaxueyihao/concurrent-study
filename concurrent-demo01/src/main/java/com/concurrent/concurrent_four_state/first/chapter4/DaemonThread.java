package com.concurrent.concurrent_four_state.first.chapter4;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class DaemonThread {


    public static void main(String[] args) {

        Thread t = new Thread(){

            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread().getName()+" running");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" done");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        // runnable -> running ->dead |-> blocked
        t.start();

        System.out.println(Thread.currentThread().getName());

    }


}
