package com.concurrent.concurrent_four_state.third.tool.chapter9;

import com.concurrent.util.ToolUtil;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class PhaserExample5 {

    private final static Random random = new Random();

    public static void main(String[] args) {
//        final Phaser phaser = new Phaser(2);
//        new Thread(phaser::arrive).start();
//        ToolUtil.sleep(4,TimeUnit.SECONDS);


        final Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new ArriveTask(phaser,i).start();
        }
        phaser.arriveAndAwaitAdvance();

        System.out.println("The phase 1 work finished ");










    }


    private static class ArriveTask extends Thread{

        private final Phaser phaser;

        public ArriveTask(Phaser phaser,int no) {
            super(String.valueOf(no));
            this.phaser = phaser;

        }

        @Override
        public void run() {
            System.out.println(getName()+" start working .");
            ToolUtil.sleep(random.nextInt(5),TimeUnit.SECONDS);

            System.out.println(getName()+" The phase one is working");
            phaser.arrive();
            ToolUtil.sleep(random.nextInt(5),TimeUnit.SECONDS);

            System.out.println(getName()+" keep to do other thing.");

        }


    }

}
