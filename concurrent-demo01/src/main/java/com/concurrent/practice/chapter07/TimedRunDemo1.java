package com.concurrent.practice.chapter07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 该中断方法，破坏了以下规则：
 * 1、在中断之前，应该了解它的中断策略。
 * 2、timeRun可以从任意一个线程中调用，因此无法知道这个调用线程的中断策略
 * 3、若任务超时之前完成，那么中断timedRun所在线程的取消任务将在timedRun返回到调用者之后启动。
 */
public class TimedRunDemo1 {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(4);


    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {

                taskThread.interrupt();
            }
        },timeout,unit);

        r.run();
    }


}
