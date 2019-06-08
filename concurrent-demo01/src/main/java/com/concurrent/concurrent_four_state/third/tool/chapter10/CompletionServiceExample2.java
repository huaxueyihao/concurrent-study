package com.concurrent.concurrent_four_state.third.tool.chapter10;

import com.concurrent.util.ToolUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CompletionServiceExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
            ToolUtil.sleep(10, TimeUnit.SECONDS);
            return 10;
        }, () -> {
            ToolUtil.sleep(20, TimeUnit.SECONDS);
            return 20;
        });

        final ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        List<Future<Integer>> futures = new ArrayList<>();
        callableList.stream().forEach(callable -> futures.add(completionService.submit(callable)));

//        Future<Integer> future = null;
//
//        while ((future = completionService.take()) != null){
//            System.out.println(future.get());
//        }
        System.out.println(completionService.poll(11, TimeUnit.SECONDS).get());





    }


}
