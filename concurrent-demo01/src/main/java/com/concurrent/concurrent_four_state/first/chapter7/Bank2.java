package com.concurrent.concurrent_four_state.first.chapter7;


/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class Bank2 {

    public static void main(String[] args) {
        TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        Thread t1 = new Thread(ticketWindow, "一号窗口");
        Thread t2 = new Thread(ticketWindow, "二号窗口");
        Thread t3 = new Thread(ticketWindow, "三号窗口");

        t1.start();
        t2.start();
        t3.start();


    }

}
