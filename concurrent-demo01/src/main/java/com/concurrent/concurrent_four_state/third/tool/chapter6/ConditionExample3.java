package com.concurrent.concurrent_four_state.third.tool.chapter6;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ConditionExample3 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition PRODUCE_COND = lock.newCondition();

    private final static Condition CONSUMER_COND = lock.newCondition();


    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) {
        IntStream.range(0,6).boxed().forEach(ConditionExample3::beginProduce);

        IntStream.range(0,13).boxed().forEach(ConditionExample3::beginConsumer);

        for (;;){
            sleep(5);
            System.out.println("====================");

            System.out.println("PRODUCE_COND.getWaitQueueLength="+lock.getWaitQueueLength(PRODUCE_COND));
            System.out.println("CONSUMER_COND.getWaitQueueLength="+lock.getWaitQueueLength(CONSUMER_COND));
            System.out.println("PRODUCE_COND.hasWaiters="+lock.hasWaiters(PRODUCE_COND));
            System.out.println("CONSUMER_COND.hasWaiters="+lock.getWaitQueueLength(CONSUMER_COND));
        }

    }

    public static void beginProduce(int i) {
        new Thread(() -> {
            for (; ; ) {
                produce();
                sleep(2);
            }
        }, "-P-" + i).start();
    }

    public static void beginConsumer(int i) {
        new Thread(() -> {
            for (; ; ) {
                consume();
                sleep(3);
            }
        }, "-C-" + i).start();
    }

    private static void produce() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() > MAX_CAPACITY) {
                PRODUCE_COND.await();
            }

            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " -P-" + value);
            TIMESTAMP_POOL.addLast(value);

            CONSUMER_COND.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private static void consume() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUMER_COND.await();
            }

            Long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + " -P-" + value);
            PRODUCE_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
