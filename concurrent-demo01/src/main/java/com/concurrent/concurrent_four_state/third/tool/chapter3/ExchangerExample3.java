package com.concurrent.concurrent_four_state.third.tool.chapter3;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExchangerExample3 {

    public static void main(String[] args) {
        final Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread() {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference(1);
                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread A has value:" + value.get());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference(2);
                try {
                    while (true) {
                        value.set(exchanger.exchange(value.get()));
                        System.out.println("Thread B has value:" + value.get());
                        Thread.sleep(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

}
