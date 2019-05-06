package com.concurrent.concurrent_four_state.first.chapter2;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class Bank {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow("1号柜台");
        ticketWindow.start();

        TicketWindow ticketWindow2 = new TicketWindow("2号柜台");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("3号柜台");
        ticketWindow3.start();
    }


}
