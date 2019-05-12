package com.concurrent.concurrent_four_state.first.chapter9;

import java.util.stream.Stream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ProduceConsumerVersion3 {

    private int i = 0;
    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P->" + i);
            // TODO
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    public static void main(String[] args) {

        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();

        Stream.of("P1", "P2").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                    }
                }
            }.start();
        });


        Stream.of("C1", "C2").forEach(n -> {

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        pc.consume();
                    }
                }
            }.start();
        });


    }


}
