package com.concurrent.concurrent_four_state.third.tool.chapter5;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static sun.jvm.hotspot.runtime.PerfMemory.start;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ReentrantLockExample {

    private static final Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {

//        IntStream.range(0,2).forEach(i->new Thread(){
//            @Override
//            public void run() {
//                needLock();
//            }
//        }.start());

        Thread thread = new Thread(() -> testUnInteeruptibly());
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread thread2 = new Thread(() -> testUnInteeruptibly());
        thread2.start();


    }

    public static void testUnInteeruptibly(){
        try{
            lock.lock();
            Optional.of("The thread-"+Thread.currentThread().getName()+" get lock and willing do working.").ifPresent(System.out::println);
            while (true){

            }

        } finally {
            lock.unlock();
        }
    }

    public static void needLock(){
        try{
            lock.lock();
            Optional.of("The thread-"+Thread.currentThread().getName()+" get lock and willing do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(5);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync(){
        synchronized (ReentrantLockExample.class){

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }



}
