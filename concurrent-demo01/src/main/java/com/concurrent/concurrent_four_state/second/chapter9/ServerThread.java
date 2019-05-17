package com.concurrent.concurrent_four_state.second.chapter9;

import java.util.Random;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ServerThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag) {
            Request request = queue.getRequest();
            if (null == request) {
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server ->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
