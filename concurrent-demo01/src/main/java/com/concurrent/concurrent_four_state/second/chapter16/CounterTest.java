package com.concurrent.concurrent_four_state.second.chapter16;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class CounterTest {

    public static void main(String[] args) throws InterruptedException {

        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10_000);
        counterIncrement.close();

    }


}
