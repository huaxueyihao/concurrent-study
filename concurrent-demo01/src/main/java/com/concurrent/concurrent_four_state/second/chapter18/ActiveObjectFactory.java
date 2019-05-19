package com.concurrent.concurrent_four_state.second.chapter18;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public final class ActiveObjectFactory {

    private ActiveObjectFactory(){
    }

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread,servant);
        schedulerThread.start();
        return proxy;
    }




}
