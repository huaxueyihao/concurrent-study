package com.concurrent.practice_video.safe_risk;

public class Target implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"....");
        }
    }
}
