package com.concurrent.practice_video.safe_risk;

public class Demo3 {

    private  boolean run = false;

    public static void main(String[] args) {

        Demo3 demo3 = new Demo3();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10; i++) {
                    System.out.println("执行了第"+i+"次");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                demo3.run = true;
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!demo3.run){
                   //不执行
                }
                System.out.println("线程2执行了");
            }
        }).start();

    }

}
