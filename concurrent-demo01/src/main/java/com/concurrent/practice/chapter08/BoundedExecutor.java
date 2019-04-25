package com.concurrent.practice.chapter08;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用semaphore来控制任务的提交速率
 */
@ThreadSafe
public class BoundedExecutor {

    private final Executor exec;

    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, int bound) {
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException{
        semaphore.acquire();
        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    }finally {
                        semaphore.release();
                    }

                }
            });
        }catch (RejectedExecutionException e){
            semaphore.release();
        }
    }
}
