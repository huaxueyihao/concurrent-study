package com.concurrent.concurrent_four_state.second.chapter4;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary string:" + Integer.toBinaryString(subject.getState()));
    }
}
