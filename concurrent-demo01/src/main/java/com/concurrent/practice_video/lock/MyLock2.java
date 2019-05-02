package com.concurrent.practice_video.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock2 implements Lock {


    private Helper helper = new Helper();


    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

    private class Helper extends AbstractQueuedSynchronizer {


        @Override
        protected boolean tryAcquire(int arg) {
            // 若果第一个线程进来，可以拿到锁，可以返回true

            // 如果第二个线程进来，拿不到锁，返回false

            // 如果当前进来的线程和当前保存的线程是同一个线程，则可以拿到锁，代价，更新状态值

            // 如何判断是第一个线程进来，还是第二个线程进来

            int state = getState();

            Thread t = Thread.currentThread();

            if (state == 0) {
                // 第一次进来
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else  if(getExclusiveOwnerThread() == t){
                setState(state + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 锁的获取和释放肯定是一一对应的，那么调用此方法的线程一定是当前线程
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new RuntimeException();
            }
            boolean flag = false;
            int state = getState() - arg;
            if (state == 0) {
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);
            return flag;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        Condition newCondition() {
            return new ConditionObject();
        }


    }


}
