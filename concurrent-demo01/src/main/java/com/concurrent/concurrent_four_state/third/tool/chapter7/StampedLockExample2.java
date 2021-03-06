package com.concurrent.concurrent_four_state.third.tool.chapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class StampedLockExample2 {


    /**
     * ReentrantLock Vs Synchronized
     * <p></p>
     * <p>
     * <p>
     * ReentrantReadWriteLock
     *
     * @param args
     */

    private final static StampedLock lock = new StampedLock();

    private final static List<Long> DATA = new ArrayList<>();


    public static void main(String[] args) {

        final ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable readTask = () -> {
            for (; ; ) {
                read();
            }
        };

        Runnable writeTask = () -> {
            for (; ; ) {
                write();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);
        executor.submit(writeTask);


    }

    private static void read() {
        long stamp = lock.tryOptimisticRead();
        if (lock.validate(stamp)) {
            try {
                stamp = lock.readLock();
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#","-R-",""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        }


    }

    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            long value = System.currentTimeMillis();
            DATA.add(value);
            System.out.println("-W-"+value);
//            TimeUnit.SECONDS.sleep(2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }


}
