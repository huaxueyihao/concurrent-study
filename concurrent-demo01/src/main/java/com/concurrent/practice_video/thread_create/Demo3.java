package com.concurrent.practice_video.thread_create;

/**
 * 使用匿名内部类
 */
public class Demo3 {


    public static void main(String[] args) {

//        new Thread(){
//            @Override
//            public void run() {
//                System.out.println("thread start....");
//            }
//        }.start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("thread start....");
//            }
//        });

        // Thread是Runnable的子类的，调用子类的方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable....");
            }
        }) {
            @Override
            public void run() {
                System.out.println("sub");
            }
        }.start();


    }
}
