package com.concurrent.concurrent_four_state.first.chapter10;

import java.util.Collection;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public interface Lock {


    class TimeOutException extends Exception{
        public TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException,TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlokedSize();





}
