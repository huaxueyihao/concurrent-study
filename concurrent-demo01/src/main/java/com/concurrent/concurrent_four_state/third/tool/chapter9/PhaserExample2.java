package com.concurrent.concurrent_four_state.third.tool.chapter9;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class PhaserExample2 {

    private final static Random random = new Random();

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 5; i++) {
            new Athletes(phaser, i).start();
        }

        new InjuredAthletes(phaser,5).start();


    }

    static class Athletes extends Thread {

        private final Phaser phaser;

        private final int no;

        public Athletes(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {

            try {
                sport(phaser, no + ":start running ", no + ": end running ");
                sport(phaser, no + ":start bicycle ", no + ": end bicycle ");
                sport(phaser, no + ":start  long jump ", no + ": end  long jump ");



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class InjuredAthletes extends Thread {

        private final Phaser phaser;

        private final int no;

        public InjuredAthletes(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {

            try {
                sport(phaser, no + ":start running ", no + ": end running ");
                sport(phaser, no + ":start bicycle ", no + ": end bicycle ");
                System.out.println("oh shit, i am injured ,i will be exited");

                phaser.arriveAndDeregister();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void sport(Phaser phaser, String start, String end) throws InterruptedException {
        System.out.println(start);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(end);
        phaser.arriveAndAwaitAdvance();

    }

}
