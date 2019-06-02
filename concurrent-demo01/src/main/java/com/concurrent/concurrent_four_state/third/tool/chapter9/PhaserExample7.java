package com.concurrent.concurrent_four_state.third.tool.chapter9;

import com.concurrent.util.ToolUtil;
import sun.jvm.hotspot.opto.Phase;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class PhaserExample7 {

    public static void main(String[] args) {
//        final Phaser phaser = new Phaser(3);
//        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
//        thread.start();
//
//        System.out.println("========================");
//        ToolUtil.sleep(5, TimeUnit.SECONDS);
//
//        thread.interrupt();
//        System.out.println("=======thread interrupt=======");

        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(()->{
            try {
                phaser.awaitAdvanceInterruptibly(10);
                System.out.println("************");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("==========");
        ToolUtil.sleep(10,TimeUnit.SECONDS);
        thread.interrupt();
        System.out.println("=====thread interrupt======");



    }


}
