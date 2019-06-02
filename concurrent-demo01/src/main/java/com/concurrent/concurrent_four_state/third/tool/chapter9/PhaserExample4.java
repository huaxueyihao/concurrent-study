package com.concurrent.concurrent_four_state.third.tool.chapter9;

import java.sql.SQLOutput;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class PhaserExample4 {

    public static void main(String[] args) throws InterruptedException {
//        final Phaser phaser = new Phaser(1);

//        phaser.arriveAndAwaitAdvance();
//        System.out.println(phaser.getPhase());
//
//        phaser.arriveAndAwaitAdvance();
//        System.out.println(phaser.getPhase());
//
//        phaser.arriveAndAwaitAdvance();
//        System.out.println(phaser.getPhase());


//        System.out.println(phaser.getRegisteredParties());
//        phaser.register();
//
//        System.out.println(phaser.getRegisteredParties());
//        phaser.register();
//
//        System.out.println(phaser.getRegisteredParties());
//        phaser.register();


//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());

//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());
//
//        new Thread(phaser::arriveAndAwaitAdvance).start();
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("==================");
//        System.out.println(phaser.getRegisteredParties());
//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());


        final Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return true;
            }
        };


        new OnAdvanceTask("Alex", phaser).start();
        new OnAdvanceTask("Jack", phaser).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());

    }


    static class OnAdvanceTask extends Thread {

        private final String name;

        private final Phaser phaser;

        public OnAdvanceTask(String name, Phaser phaser) {
            super(name);
            this.name = name;
            this.phaser = phaser;
        }


        @Override
        public void run() {
            System.out.println(getName() + " I am start  and the phase."+ phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " I am end and the phaser.");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(getName().equals("Alex")){
                System.out.println(getName() + " I am start and the phase." + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + " I am end and the phaser.");

            }


        }


    }


}
