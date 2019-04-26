package com.concurrent.practice_video.thread_create;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 */
public class Demo5 {

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timetask is run");
            }
        },0,1000);
    }

}
