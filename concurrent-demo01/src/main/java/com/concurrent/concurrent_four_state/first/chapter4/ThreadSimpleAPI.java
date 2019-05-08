package com.concurrent.concurrent_four_state.first.chapter4;

import java.util.Optional;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadSimpleAPI {


    public static void main(String[] args) {
        Thread t = new Thread(()->{

            Optional.of("Hello").ifPresent(System.out::println);

            try {
                Thread.sleep(1_0000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1");
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }

}
