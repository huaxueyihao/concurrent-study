package com.concurrent.concurrent_four_state.first.chapter7;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class TicketWindowRunnable implements Runnable{

    private static final int MAX = 500;

    private static int index = 1;

    private final Object MONITOR = new Object();


    @Override
    public void run() {
        synchronized (MONITOR){
            while (true){
                if(index > MAX){
                    break;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 的号码是："+(index++));
            }
        }
    }
}
