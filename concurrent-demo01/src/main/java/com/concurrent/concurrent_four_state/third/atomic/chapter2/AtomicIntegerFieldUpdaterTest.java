package com.concurrent.concurrent_four_state.third.atomic.chapter2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {

        final AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        ;
        final TestMe me = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    final int MAX = 20;
                    for (int j = 0; j < MAX; j++) {
                        int v = updater.incrementAndGet(me);
                        System.out.println(Thread.currentThread().getName() + "=>" + v);
                    }
                }
            }.start();
        }


    }

    static class TestMe {
        volatile int i;
    }


}
