package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import javax.tools.Tool;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutorServiceExample3 {

    public static void main(String[] args) {

//        testAllowCoreThreadTimeOut();
//        testRemove();

//        testPestartCoreThread();

        testThreadPoolAdvice();

    }

    private static void test() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executorService.getActiveCount());

        executorService.execute(() -> {
            ToolUtil.sleep(10, TimeUnit.SECONDS);

        });

        ToolUtil.sleep(20, TimeUnit.MILLISECONDS);

        System.out.println(executorService.getActiveCount());
    }

    private static void testAllowCoreThreadTimeOut() {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executorService.getActiveCount());

        executorService.setKeepAliveTime(20, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);

        IntStream.range(0, 5).boxed().forEach(i -> {
            executorService.execute(() -> {
                ToolUtil.sleep(3, TimeUnit.SECONDS);
            });
        });
    }

    private static void testRemove() {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executorService.getActiveCount());

        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);

        IntStream.range(0, 5).boxed().forEach(i -> {
            executorService.execute(() -> {
                ToolUtil.sleep(3, TimeUnit.SECONDS);
                System.out.println("==============i am finished ");
            });
        });


//        ToolUtil.sleep(20, TimeUnit.SECONDS);
        Runnable r = () -> {
            ToolUtil.sleep(20, TimeUnit.SECONDS);
            System.out.println("I will never be executed ");
        };
        executorService.execute(r);
        ToolUtil.sleep(20, TimeUnit.MILLISECONDS);
        executorService.remove(r);

    }

    private static void testPestartCoreThread() {
        ThreadPoolExecutor executorSevice = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executorSevice.getActiveCount());

        executorSevice.prestartCoreThread();
        System.out.println(executorSevice.getActiveCount());

        executorSevice.prestartAllCoreThreads();


        executorSevice.prestartCoreThread();
        System.out.println(executorSevice.getActiveCount());

        executorSevice.execute(() -> {
            ToolUtil.sleep(1, TimeUnit.SECONDS);
        });

        executorSevice.execute(() -> {
            ToolUtil.sleep(1, TimeUnit.SECONDS);
        });

        executorSevice.prestartCoreThread();
        System.out.println(executorSevice.getActiveCount());

    }

    private static void testThreadPoolAdvice() {

        MyThreadPoolExecutor executorService = new MyThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(new MyRunnable(1) {
            @Override
            public void run() {
                System.out.println("==========");
            }
        });

    }

    private abstract static class MyRunnable implements Runnable {

        private final int no;

        public MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return this.no;
        }

    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(" init the " + ((MyRunnable) r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {

            if (null == t) {
                System.out.println(" sucess the " + ((MyRunnable) r).getData());
            } else {
                System.out.println("fail");
            }
        }
    }


}
