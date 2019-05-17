package com.concurrent.concurrent_four_state.second.chapter7;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class UsePersonThread extends Thread {

    private Person person;


    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" print "+person.toString());
        }
    }
}
