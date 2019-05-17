package com.concurrent.concurrent_four_state.second.chapter9;

import java.util.LinkedList;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();


    public Request getRequest(){
        synchronized (queue){
            while (queue.size() <= 0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request){
        synchronized (queue){
            queue.addLast(request);
            queue.notifyAll();
        }
    }





}
