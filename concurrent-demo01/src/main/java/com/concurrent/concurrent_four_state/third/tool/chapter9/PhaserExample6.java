package com.concurrent.concurrent_four_state.third.tool.chapter9;

import com.concurrent.util.ToolUtil;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class PhaserExample6 {


    public static void main(String[] args) {
//        final Phaser phaser = new Phaser(6);

//        new Thread(()->phaser.awaitAdvance(phaser.getPhase())).start();
//        new Thread(()->phaser.arrive()).start();
//
//        ToolUtil.sleep(3, TimeUnit.SECONDS);
//
//        System.out.println(phaser.getArrivedParties());


        final Phaser phaser = new Phaser(6);
        IntStream.rangeClosed(0, 5).boxed().map(i -> phaser).forEach(AwaitAdvanceTask::new);
        phaser.awaitAdvance(phaser.getPhase());

        System.out.println("=============================");


    }

    private static class AwaitAdvanceTask extends Thread {

        private final Phaser phaser;

        public AwaitAdvanceTask(Phaser phaser) {
            this.phaser = phaser;
            start();
        }


        @Override
        public void run() {
            ToolUtil.sleep(5, TimeUnit.SECONDS);

//            phaser.arriveAndAwaitAdvance();
            phaser.arrive();
            System.out.println(getName() + "finished work");
        }
    }
}
