package com.concurrent.practice_video.lock;

public class MainClass {

    private MyLock2 lock = new MyLock2();

    private int value;

    public int next(){
        lock.lock();
        try {
            Thread.sleep(300);
            return value++;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }

    public void a(){
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    private void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        MainClass m = new MainClass();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
//                    System.out.println(Thread.currentThread().getName()+" "+m.next());
                    m.a();
                }
            }
        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println(Thread.currentThread().getName()+" "+m.next());
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println(Thread.currentThread().getName()+" "+m.next());
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println(Thread.currentThread().getName()+" "+m.next());
//                }
//            }
//        }).start();

    }



}
