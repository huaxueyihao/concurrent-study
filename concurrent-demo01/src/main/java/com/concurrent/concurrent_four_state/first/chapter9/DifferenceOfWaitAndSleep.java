package com.concurrent.concurrent_four_state.first.chapter9;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class DifferenceOfWaitAndSleep {


    private static final Object LOCK = new Object();

    public static void main(String[] args) {


        m2();

    }

    public static void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m2(){
        synchronized (LOCK){
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }




}
