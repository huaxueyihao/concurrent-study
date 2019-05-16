package com.concurrent.concurrent_four_state.second.chapter6;

import java.util.Random;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class WriterWorker extends Thread {

    private static final Random random = new Random();

    private final SharedData data;

    private final String filler;

    private int index = 0;

    public WriterWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
            try {
                while (true){
                    char c = nextChar();
                    data.write(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if(index >= filler.length()){
            index = 0;
        }
        return c;
    }
}
