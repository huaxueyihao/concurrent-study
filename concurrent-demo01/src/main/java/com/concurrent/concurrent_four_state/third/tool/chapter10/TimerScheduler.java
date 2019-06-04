package com.concurrent.concurrent_four_state.third.tool.chapter10;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class TimerScheduler {


    public static void main(String[] args) {

        Timer timer = new Timer();

        final TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("======" + System.currentTimeMillis());

            }
        };

        timer.schedule(timerTask, 1000, 1000);


    }


}
