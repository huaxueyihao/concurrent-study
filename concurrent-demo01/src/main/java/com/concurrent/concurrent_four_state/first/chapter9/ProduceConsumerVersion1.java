package com.concurrent.concurrent_four_state.first.chapter9;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ProduceConsumerVersion1 {

    private int i = 1;

    private final Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            System.out.println("P->" + (i++));
        }
    }

    private void consume() {
        synchronized (LOCK) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {

        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();

        new Thread("P") {
            @Override
            public void run() {
                while (true)
                    pc.produce();
            }
        }.start();


        new Thread("C") {
            @Override
            public void run() {
                while (true)
                    pc.consume();
            }
        }.start();
    }


}
