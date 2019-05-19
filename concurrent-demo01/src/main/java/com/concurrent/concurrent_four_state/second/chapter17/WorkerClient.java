package com.concurrent.concurrent_four_state.second.chapter17;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class WorkerClient {

    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex",channel).start();
        new TransportThread("Jack",channel).start();
        new TransportThread("William",channel).start();

    }

}
