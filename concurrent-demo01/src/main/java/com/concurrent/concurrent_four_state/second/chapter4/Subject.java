package com.concurrent.concurrent_four_state.second.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Subject {

    private List<Observer> observers = new ArrayList();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if(state == this.state){
            return;
        }
        this.state = state;
        notifyAllObjserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObjserver(){
        observers.stream().forEach(Observer::update);
    }



}
