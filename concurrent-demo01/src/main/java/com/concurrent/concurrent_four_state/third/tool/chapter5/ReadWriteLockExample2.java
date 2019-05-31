package com.concurrent.concurrent_four_state.third.tool.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ReadWriteLockExample2 {

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static Lock readLock = lock.readLock();
    private final static Lock writeLock = lock.writeLock();

    private final static List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(ReadWriteLockExample2::read);
        thread1.start();

//        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(ReadWriteLockExample2::read);
        thread2.start();
    }

    public static void write() {
        try {
            readLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void read() {
        try {
            writeLock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " ==========================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }


}
