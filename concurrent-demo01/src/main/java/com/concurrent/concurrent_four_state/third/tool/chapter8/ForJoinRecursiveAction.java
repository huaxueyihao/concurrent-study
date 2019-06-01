package com.concurrent.concurrent_four_state.third.tool.chapter8;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ForJoinRecursiveAction {

    private final static int MAX_THRESHOLD = 3;

    private final static AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursiceAction(0, 10));
        forkJoinPool.awaitQuiescence(10, TimeUnit.MICROSECONDS);
        Optional.of(SUM).ifPresent(System.out::println);
    }

    private static class CalculateRecursiceAction extends RecursiveAction {

        private final int start;

        private final int end;

        public CalculateRecursiceAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculateRecursiceAction leftAction = new CalculateRecursiceAction(start, middle);
                CalculateRecursiceAction rightAction = new CalculateRecursiceAction(middle + 1, end);

                leftAction.fork();
                rightAction.fork();

            }

        }
    }

}
