package com.concurrent.concurrent_four_state.third.atomic.chapter1;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicIntegerDetailTest {

    public static void main(String[] args) {

//        AtomicInteger i = new AtomicInteger();
//        System.out.println(i.get());
//
//        i = new AtomicInteger(10);
//        System.out.println(i.get());
//        i.lazySet(13);

        // get and set

        AtomicInteger getAndSet = new AtomicInteger(10);
        int result = getAndSet.getAndAdd(10);
        System.out.println(result);
        System.out.println(getAndSet.get());;


    }

}
