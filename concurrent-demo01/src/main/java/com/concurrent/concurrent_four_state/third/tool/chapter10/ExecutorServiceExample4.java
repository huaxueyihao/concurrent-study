package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutorServiceExample4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        testInvokeAny();

        testInvokeAnyTimeout();
    }

    /**
     * {@link ExecutorService#invokeAny(Collection)}
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
//            ToolUtil.sleep(ThreadLocalRandom.current().nextInt(20), TimeUnit.SECONDS);
//            System.out.println(Thread.currentThread().getName() + ":" + i);
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(100));

            System.out.println(Thread.currentThread().getName() + ":" + i);
            return i;
        }).collect(Collectors.toList());

        Integer value = executorService.invokeAny(callableList);
        System.out.println("===========install==========");
        System.out.println(value);

    }

    /**
     * {@link ExecutorService#invokeAny(Collection)}
     */
    private static void testInvokeAnyTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Integer value = executorService.invokeAny(IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
//            ToolUtil.sleep(ThreadLocalRandom.current().nextInt(20), TimeUnit.SECONDS);
//            System.out.println(Thread.currentThread().getName() + ":" + i);
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));

            System.out.println(Thread.currentThread().getName() + ":" + i);
            return i;
        }).collect(Collectors.toList()), 3, TimeUnit.SECONDS);

        System.out.println("===========install==========");
        System.out.println(value);

    }

}
