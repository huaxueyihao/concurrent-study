package com.concurrent.concurrent_four_state.second.chapter14;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CustomCountDown {

    private final int total;

    private int counter = 0;

    public CustomCountDown(int total){
        this.total = total;
    }

    public void down(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while (counter != total){
                this.wait();
            }
        }
    }

}
