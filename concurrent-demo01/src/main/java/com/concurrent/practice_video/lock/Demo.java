package com.concurrent.practice_video.lock;

/**
 * synchronized重入锁
 */
public class Demo {

    public synchronized void a() {
        System.out.println("a");
        b();
    }

    public synchronized void b() {
        System.out.println("b");
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Demo demo = new Demo();
                demo.a();
            }
        }).start();


    }


}
