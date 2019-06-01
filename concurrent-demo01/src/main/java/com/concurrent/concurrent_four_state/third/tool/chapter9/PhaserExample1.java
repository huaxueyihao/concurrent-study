package com.concurrent.concurrent_four_state.third.tool.chapter9;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class PhaserExample1 {

    private final static Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) {

        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1,5).boxed().map(i->phaser).forEach(Task::new);
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("all of worker finished the task");




    }

    static class Task extends Thread {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            start();
        }


        @Override
        public void run() {

            System.out.println("The Worker [" + getName() + "] is working");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(1));


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();

        }
    }


}
