package com.concurrent.concurrent_four_state.second.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class MessageHandler {

    private final static Random random = new Random(System.currentTimeMillis());


    private final static Executor executor = Executors.newFixedThreadPool(5);


    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown(){
        ((ExecutorService) executor).shutdown();
    }



}
