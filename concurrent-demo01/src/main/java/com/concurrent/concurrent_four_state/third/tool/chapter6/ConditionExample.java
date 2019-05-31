package com.concurrent.concurrent_four_state.third.tool.chapter6;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ConditionExample {


    private final static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;


    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();


    }

    private static void buildData() {
        try {
            lock.lock();
            while (noUse) {
                condition.await();
            }

            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signal(); // monitor.notify
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // synchronized end #monitor end
        }
    }


    private static void useData() {
        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            noUse = false;

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }


}
