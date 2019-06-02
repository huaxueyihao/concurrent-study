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
public class PhaserExample8 {

    public static void main(String[] args) {

        final Phaser phaser = new Phaser(1);
        new Thread(phaser::arriveAndAwaitAdvance).start();

        ToolUtil.sleep(3, TimeUnit.SECONDS);
        System.out.println(phaser.isTerminated());
        phaser.forceTermination();
        System.out.println(phaser.isTerminated());



    }
}
