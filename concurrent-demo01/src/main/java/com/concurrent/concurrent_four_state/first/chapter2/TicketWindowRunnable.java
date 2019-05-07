package com.concurrent.concurrent_four_state.first.chapter2;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class TicketWindowRunnable  implements Runnable{

    private static final int MAX = 50;

    private static int index = 1;


    @Override
    public void run() {

        while (index <= MAX){
            System.out.println(Thread.currentThread().getName()+" 的号码是："+(index++));
        }


    }
}
