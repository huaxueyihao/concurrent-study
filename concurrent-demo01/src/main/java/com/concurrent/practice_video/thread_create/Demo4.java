package com.concurrent.practice_video.thread_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo4 implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        System.out.println("正在进行计算.....");
        Thread.sleep(3000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Demo4 demo4 = new Demo4();
        FutureTask<Integer> tasks = new FutureTask<Integer>(demo4);

        Thread t = new Thread(tasks);
        t.start();

        System.out.println("我先干点别的。。。。");
        Integer result = tasks.get();
        System.out.println("线程执行的结果为：" + result);

    }
}
