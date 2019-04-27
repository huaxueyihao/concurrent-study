package com.concurrent.practice_video.safe_risk;

public class Demo {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Target());
        Thread t2 = new Thread(new Target());
        Thread t3 = new Thread(new Target());
        Thread t4 = new Thread(new Target());
        Thread t5 = new Thread(new Target());

        t1.setPriority(10);
        t2.setPriority(1);

        t1.start();
        t2.start();



    }

}
