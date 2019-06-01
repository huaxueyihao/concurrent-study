package com.concurrent.concurrent_four_state.third.tool.chapter8;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ForkJoinRecuresiveTask {


    private final static int MAX_THRESHOLD = 3;

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculatedRecurisiveTask(0, 10));
        try {
            Integer sum = future.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private static class CalculatedRecurisiveTask extends RecursiveTask<Integer> {

        private final int start;

        private final int end;

        public CalculatedRecurisiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= MAX_THRESHOLD) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                int middle = (start + end) / 2;

                CalculatedRecurisiveTask leftTask = new CalculatedRecurisiveTask(start, middle);
                CalculatedRecurisiveTask rightTask = new CalculatedRecurisiveTask(middle + 1, end);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }

        }
    }

}
