package com.concurrent.concurrent_four_state.second.chapter4;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }


    @Override
    public void update() {
        System.out.println("Octal string:" + Integer.toOctalString(subject.getState()));
    }
}
