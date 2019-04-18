package com.concurrent.study.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程唤醒
 */
public class ReenterLockCondition implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();


    @Override
    public void run() {

        try {
            lock.lock();
            // 线程等待状态
            condition.await();
            System.out.println("Thread is going on");

        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }


    }


    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        // 唤醒线程的执行
        condition.signal();
        lock.unlock();

    }

}
