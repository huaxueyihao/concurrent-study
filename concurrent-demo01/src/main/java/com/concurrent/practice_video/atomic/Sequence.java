package com.concurrent.practice_video.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class Sequence {

    private AtomicInteger value = new AtomicInteger();

    private int[] s = {2,1,4,6};

    AtomicIntegerArray a = new AtomicIntegerArray(s);

    AtomicReference<User> user = new AtomicReference<>();

    AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    //
    public  int getNext() {
        a.getAndIncrement(2);
        return value.getAndIncrement();
    }

    public static void main(String[] args) {

        Sequence sequence = new Sequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
