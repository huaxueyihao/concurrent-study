package com.concurrent.study.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch能够使一个或多个线程等待其他线程完成各自的工作后再执行；
 * CountDownLatch是JDK 5+里面闭锁的一个实现。
 *
 * 闭锁（Latch）：一种同步方法，可以延迟线程的进度直到线程到达某个终点状态。
 * 通俗的讲就是，一个闭锁相当于一扇大门，在大门打开之前所有线程都被阻断，一旦大门打开所有线程都将通过，但是一旦大门打开，
 * 所有线程都通过了，那么这个闭锁的状态就失效了，门的状态也就不能变了，只能是打开状态。也就是说闭锁的状态是一次性的，
 * 它确保在闭锁打开之前所有特定的活动都需要在闭锁打开之后才能完成。
 *
 * 与CountDownLatch第一次交互是主线程等待其它的线程，主线程必须在启动其它线程后立即调用await方法，
 * 这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
 *
 * 其他的N个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自的任务，这种机制就是通过countDown()方法来完成的。每调用一次这个方法，在构造函数中初始化的count值就减1，所以当N个线程都调用了这个方法count的值等于0，
 * 然后主线程就能通过await方法，恢复自己的任务。
 *
 */
public class CountDownLatchDemo {

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    static class Worker extends Thread {
        String workerName;
        int workTime;
        CountDownLatch latch;

        public Worker(String workerName, int workTime, CountDownLatch latch) {
            this.workerName = workerName;
            this.workTime = workTime;
            this.latch = latch;
        }

        public void run() {
            System.out.println("Worker " + workerName + " do work begin at " + sdf.format(new Date()));
            doWork();
            System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
            latch.countDown();// 工人完成工作，计数器减一
        }

        private void doWork() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);//两个工人的写作
        Worker worker1 = new Worker("zhang san", 5000, latch);
        Worker worker2 = new Worker("li si", 8000, latch);

        worker1.start();worker2.start();
        latch.await();// 等待所有工人完成工作
        System.out.println("all work done at "+sdf.format(new Date()));

    }


}
