package com.concurrent.practice_video.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 通过自旋实现的重入锁
 */
public class MyLock implements Lock {

    private boolean isLocked = false;

    Thread lockBy = null;

    int lockCount = 0;

    @Override
    public synchronized void lock() {
        // 自旋
        Thread currentThread = Thread.currentThread();

        while (isLocked && currentThread != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockBy = currentThread;
        lockCount++;

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        if (lockBy == Thread.currentThread()) {
            lockCount--;
            if(lockCount == 0){
                isLocked = false;
                notify();
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
