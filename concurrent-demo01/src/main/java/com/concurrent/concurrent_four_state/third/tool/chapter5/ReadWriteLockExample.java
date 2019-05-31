package com.concurrent.concurrent_four_state.third.tool.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ReadWriteLockExample {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> write());
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(ReadWriteLockExample::read);
        thread2.start();
    }

    public static void write() {
        try {
            lock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void read() {
        try {
            lock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " ==========================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
