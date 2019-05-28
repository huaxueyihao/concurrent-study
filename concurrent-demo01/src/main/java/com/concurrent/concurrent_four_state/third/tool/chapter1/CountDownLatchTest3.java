package com.concurrent.concurrent_four_state.third.tool.chapter1;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CountDownLatchTest3 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        latch.await();

        System.out.println("==============");

    }

}
