package com.concurrent.concurrent_four_state.third.tool.chapter3;

import java.util.concurrent.Exchanger;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExchangeExample2 {

    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread() {
            @Override
            public void run() {
                Object aObj = new Object();
                System.out.println("A will send the object " + aObj);
                try {
                    Object rObj = exchanger.exchange(aObj);
                    System.out.println("A reciveved the object " + rObj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Object aObj = new Object();
                System.out.println("B will send the object " + aObj);
                try {
                    Object rObj = exchanger.exchange(aObj);
                    System.out.println("B reciveved the object " + rObj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }.start();


    }


}
