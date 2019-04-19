package com.concurrent.practice.chapter05;

import java.util.Vector;

/**
 * 5.1.1 并发容器存在的问题
 */
public class VectorConcurrentTest {


    public static void main(String[] args) {

        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < 1000; i++) {
            vector.add("元素" + i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(Thread.currentThread().getName() + "最后元素:" + getLast(vector));
                }
            }
        }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        System.out.println(Thread.currentThread().getName() + "删除最后元素:" + deleteLast(vector));
                    }
                }
            }).start();

    }


    public static Object getLast(Vector list) {
        // 通过客户端枷锁解决迭代的同时，另一个线程进行修改的操作
//        synchronized (list){
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
//        }

    }

    public static Object deleteLast(Vector list) {
//        synchronized (list){
            int lastIndex = list.size() - 1;
            return list.remove(lastIndex);
//        }
    }


}
