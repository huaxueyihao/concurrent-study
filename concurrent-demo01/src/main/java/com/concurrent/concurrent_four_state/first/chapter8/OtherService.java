package com.concurrent.concurrent_four_state.first.chapter8;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class OtherService {

    private final Object lock = new Object();

    private DeadLock deadLock;



    public void s1() {
        synchronized (lock) {
            System.out.println("s1=====");
        }
    }

    public void s2() {
        synchronized (lock) {
            System.out.println("s2=====");
            deadLock.m2();
        }
    }

    public DeadLock getDeadLock() {
        return deadLock;
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
