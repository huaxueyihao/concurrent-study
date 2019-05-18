package com.concurrent.concurrent_four_state.second.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ConsumerThread extends Thread {

    private final MessageQueue messageQueue;

    private final static Random random = new Random();

    private final static AtomicInteger counter = new AtomicInteger();

    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super("CONSUMER" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " take message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
