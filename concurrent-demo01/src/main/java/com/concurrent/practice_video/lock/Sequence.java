package com.concurrent.practice_video.lock;

public class Sequence {

    private MyLock lock = new MyLock();

    private int value;

    public int getNext() {
        lock.lock();
        value++;
        lock.unlock();
        return value;
    }

    public static void main(String[] args) {

        Sequence sequence = new Sequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println(Thread.currentThread().getName() + ":" + sequence.getNext());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println(Thread.currentThread().getName() + ":" + sequence.getNext());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println(Thread.currentThread().getName() + ":" + sequence.getNext());
            }
        }).start();

    }

}
