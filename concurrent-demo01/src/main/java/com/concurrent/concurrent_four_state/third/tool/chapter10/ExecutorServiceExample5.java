package com.concurrent.concurrent_four_state.third.tool.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutorServiceExample5 {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executorService.execute(()-> System.out.println("I will be process of triggered the execute ."));
        executorService.getQueue().add(() -> System.out.println("I am added directly into the queue ."));

    }


}
