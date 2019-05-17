package com.concurrent.concurrent_four_state.second.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadLocalSimulator<T> {

    private final Map<Thread,T> storage = new HashMap<>();


    public void set(T t){
        synchronized (this){
            Thread key = Thread.currentThread();
            storage.put(key,t);
        }
    }
    
    public T get(){
        synchronized (this){
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if(null == value){
                return iniialValue();
            }
            return value;
        }
    }

    private T iniialValue() {
        return null;
    }

}
