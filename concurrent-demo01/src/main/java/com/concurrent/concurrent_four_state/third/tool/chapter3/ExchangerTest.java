package com.concurrent.concurrent_four_state.third.tool.chapter3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExchangerTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ExchangerTest.class);

    private static volatile boolean isDone = false;

    static class ExchangerProducer implements Runnable {

        private final Exchanger<Integer> exchanger;

        private static int data = 1;

        public ExchangerProducer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!Thread.interrupted() && !isDone) {
                for (int i = 0; i < 3; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        data = i;
                        System.out.println("producer before:" + data);
                        data = exchanger.exchange(data);
                        System.out.println("producer after:" + data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ExchangerConsumer implements Runnable {
        private final Exchanger<Integer> exchanger;

        private static int data = 0;

        public ExchangerConsumer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!Thread.interrupted() && !isDone) {
                data = 0;
                System.out.println("consumer before:" + data);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = exchanger.exchange(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("consumer after:" + data);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<Integer> exchanger = new Exchanger<>();
        ExchangerProducer producer = new ExchangerProducer(exchanger);
        ExchangerConsumer consumer = new ExchangerConsumer(exchanger);

        exec.execute(producer);
        exec.execute(consumer);
        exec.shutdown();

        exec.awaitTermination(30,TimeUnit.SECONDS);


    }


}
