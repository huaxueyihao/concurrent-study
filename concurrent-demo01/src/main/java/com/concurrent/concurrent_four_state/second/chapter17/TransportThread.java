package com.concurrent.concurrent_four_state.second.chapter17;

import java.util.Random;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class TransportThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random();

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (Exception e) {

        }
    }
}
