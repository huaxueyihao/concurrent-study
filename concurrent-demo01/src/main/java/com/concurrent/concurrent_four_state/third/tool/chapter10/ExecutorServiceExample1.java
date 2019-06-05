package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.sql.SQLOutput;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutorServiceExample1 {

    /**
     * The demo for class {@link java.util.concurrent.ExecutorService}
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        isShutDown();
//        isTierminated();
//        executeRunnableError();
        executeRunnableTask();
    }


    /**
     * {@link ExecutorService#isShutdown()}
     * {@link ExecutorService#shutdown()}
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            ToolUtil.sleep(5, TimeUnit.SECONDS);
        });

        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());

        executorService.execute(() -> System.out.println("i will be rejected"));


    }

    /**
     * {@link ExecutorService#isTerminated()}
     * {@link ThreadPoolExecutor#isTerminated()}
     */
    private static void isTierminated() {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            ToolUtil.sleep(2, TimeUnit.SECONDS);
        });

        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());

    }


    /**
     *
     */
    private static void executeRunnableError() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        executorService.execute(() -> {
            ToolUtil.sleep(2, TimeUnit.SECONDS);
        });

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(1 / 0)));


        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("===================");

    }

    /**
     * <pre>
     *
     *                                      |
     *                                      |
     *                                      |
     * send request ------> store db -----> |
     *                                      |
     *                                      |
     *                                      |
     * </pre>
     */
    private static void executeRunnableTask() {

    }


    private abstract static class MyTask implements Runnable {

        protected final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExeute();
                this.doone();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract void error(Throwable cause);

        protected abstract void doone();

        protected abstract void doExeute();

        protected abstract void doInit();
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {

            Thread thread = new Thread(r);
            thread.setName("My-thread-" + SEQ.getAndIncrement());

            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("The thread " + t.getName() + " execute failed");
                cause.printStackTrace();
                System.out.println("=================");
            });
            return thread;
        }


    }


}
