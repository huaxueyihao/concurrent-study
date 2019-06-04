package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutorsExample2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();

//        Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println("thread -" + Thread.currentThread().getName());
                    ToolUtil.sleep(2, TimeUnit.SECONDS);
                    return "Task-" + i;
                }
        ).collect(Collectors.toList());

        executorService.invokeAll(callableList).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);




    }
}
