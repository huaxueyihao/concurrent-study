package com.concurrent.concurrent_four_state.first.chapter5;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadJoin3 {


    public static void main(String[] args) throws InterruptedException {

        long startTimestamp = System.currentTimeMillis();

        Thread t1 = new Thread(new CaptureRunnable("M1", 10000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 30000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 15000L));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endimestamp = System.currentTimeMillis();
        System.out.println("消耗额时间startTimestamp=" + startTimestamp + " ,结束时间=" + endimestamp);

    }


}


class CaptureRunnable implements Runnable {

    private String machineName;

    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spendTime);
            System.out.println(machineName + " completed data capture and successful");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish.";
    }
}
