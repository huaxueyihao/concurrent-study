package com.concurrent.practice.chapter07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimedRunDemo2 {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(4);

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {

        class RethrowableTask implements Runnable {

            private volatile Throwable t;
            @Override
            public void run() {

                try {
                    r.run();
                }catch (Throwable t){
                    this.t = t;
                }
            }

            void rethrow(){
                if(t != null){
                     throw lanunderThrowable(t);
                }
            }
            private  RuntimeException lanunderThrowable(Throwable t) {
                if(t instanceof RuntimeException){
                    return (RuntimeException) t;
                }else if(t instanceof Error){
                    throw (Error) t;
                }else
                    throw new IllegalStateException("Not unchecked",t);
            }
        }

        RethrowableTask rethrowableTask = new RethrowableTask();
        final Thread thread = new Thread(rethrowableTask);
        thread.start();

        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                thread.interrupt();
            }
        },timeout,unit);

        thread.join(unit.toMillis(timeout));
        rethrowableTask.rethrow();


    }





}
