package com.concurrent.concurrent_four_state.first.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class BooleanLock implements Lock {

    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    // The initValue is true indicated the lock have be get;
    // The initValue is false indicated the lock is free
    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();

    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0) {
                throw new TimeOutException(Thread.currentThread().getName() + " Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " hasRemaining = " + hasRemaining);
        }
        this.initValue = true;
        this.currentThread = Thread.currentThread();


    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            System.out.println(Thread.currentThread().getName() + " release the lock monitor.");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlokedSize() {
        return blockedThreadCollection.size();
    }
}
