package com.concurrent.concurrent_four_state.first.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> {
            new Thread(() -> {
                try {
                    booleanLock.lock(10L);
                    Optional.of(Thread.currentThread().getName()+" have the lock monitor").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Lock.TimeOutException e) {
//                    e.printStackTrace();
                    System.out.println(e.getMessage());
                } finally {
                    booleanLock.unlock();

                }

            },name).start();
        });
//        Thread.sleep(100);
//        booleanLock.unlock();

    }

    private static void work() {
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);

        try {
            Thread.sleep(40_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
