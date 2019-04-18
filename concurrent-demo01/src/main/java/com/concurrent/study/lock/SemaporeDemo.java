package com.concurrent.study.lock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//Semaphore是一种计数信号量，用于管理一组资源，
// 内部是基于AQS的共享模式。它相当于给线程规定一个量从而控制允许活动的线程数
public class SemaporeDemo {

    // 非公平的信号,第二个参数为true是公平模式
    private static final Semaphore semaphore = new Semaphore(3, true);
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());


    private static class InformationThread extends Thread {

        private final String name;
        private final int age;

        public InformationThread(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":大家好，我是" + name + ",我今年" + age + "岁当前时间为：" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(name + "要准备释放许可证了，当前时间为：" + System.currentTimeMillis());
                System.out.println("当前可使用的许可数为：" + semaphore.availablePermits());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] name = {"李明", "王五", "张杰", "王强", "赵二", "李四", "张三"};
        int[] age = {26, 27, 33, 45, 29, 23, 41};
        for (int i = 0; i < 7; i++) {
            Thread t1 = new InformationThread(name[i], age[i]);
            threadPool.execute(t1);
        }

    }


}
