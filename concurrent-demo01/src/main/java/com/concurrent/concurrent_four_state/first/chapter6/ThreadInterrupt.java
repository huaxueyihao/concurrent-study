package com.concurrent.concurrent_four_state.first.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadInterrupt {


    public static void main(String[] args)  {

        Thread t = new Thread(){
            @Override
            public void run() {
//                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        e.printStackTrace();
                    }
                    //System.out.println(">>"+this.isInterrupted());
//                }
            }
        };

        t.start();
//        Thread.sleep(100);
//        System.out.println(t.isInterrupted());
//        t.interrupt();
//        System.out.println(t.isInterrupted());


        Thread main = Thread.currentThread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        e.printStackTrace();
                    }
                    main.interrupt();
                    //System.out.println(">>"+this.isInterrupted());
                }
            }
        };

        t2.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("main线程打断");
            e.printStackTrace();
        }


    }

}
