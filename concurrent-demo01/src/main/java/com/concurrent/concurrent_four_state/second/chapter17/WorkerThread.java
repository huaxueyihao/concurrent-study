package com.concurrent.concurrent_four_state.second.chapter17;

import java.util.Random;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class WorkerThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random();


    public WorkerThread(String s, Channel channel) {
        super(s);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            channel.take().execute();
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
