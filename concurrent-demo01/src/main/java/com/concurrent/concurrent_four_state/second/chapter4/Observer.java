package com.concurrent.concurrent_four_state.second.chapter4;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public abstract class Observer {

    protected Subject subject;

    public Observer() {
    }

    public Observer(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }


    public abstract void update();
}
