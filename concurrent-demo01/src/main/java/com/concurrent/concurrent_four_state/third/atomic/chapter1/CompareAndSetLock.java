package com.concurrent.concurrent_four_state.third.atomic.chapter1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException {

        boolean success = value.compareAndSet(0, 1);


        System.out.println("tryLock=" + success);
        if (!success) {
            throw new GetLockException("Get the lock failed");
        } else {
            lockedThread = Thread.currentThread();
        }

    }


    public void unlock() {
        if (0 == value.get()) {
            return;
        }
        if (lockedThread == Thread.currentThread()) {
            boolean success = value.compareAndSet(1, 0);
            System.out.println("unlock=" + success);
        }


    }


}
