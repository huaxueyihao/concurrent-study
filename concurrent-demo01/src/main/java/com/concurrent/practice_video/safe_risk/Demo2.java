package com.concurrent.practice_video.safe_risk;

/**
 * 保证可见性
 * <p>
 * 多个线程拿到的是同一把锁
 */
public class Demo2 {

    private volatile int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {

        Demo2 demo2 = new Demo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.setA(10);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(demo2.getA());
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终结果为：" + demo2.getA());
    }
}
