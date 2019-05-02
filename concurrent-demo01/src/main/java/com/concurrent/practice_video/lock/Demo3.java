package com.concurrent.practice_video.lock;

public class Demo3 {

    MyLock lock = new MyLock();

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {

        lock.lock();
        System.out.println("b");
        lock.unlock();

    }

    public static void main(String[] args) {

        Demo3 demo3 = new Demo3();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true)
                    demo3.a();
            }
        }).start();


    }


}
