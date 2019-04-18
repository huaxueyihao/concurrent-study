package com.concurrent.study.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock2 implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;


    public void run() {
        for (int j = 0; j < 100000000; j++) {
            lock.lock();
            lock.lock();// 多加层锁，若不释放 则会死锁阻塞
            try {
                i++;
            } finally {
                // 释放锁必须写在finally
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ReenterLock2 reenterLock = new ReenterLock2();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);


    }
}
