package com.concurrent.concurrent_four_state.second.chapter13;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ProducerAndConsumerClient {

    public static void main(String[] args) {

        final MessageQueue messageQueue = new MessageQueue();

        new ProducerThread(messageQueue, 1).start();
        new ConsumerThread(messageQueue, 1).start();


    }
}
