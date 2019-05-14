package com.concurrent.concurrent_four_state.second.chapter1;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SingletonObject3 {

    private static SingletonObject3 instance ;

    private SingletonObject3(){

    }

    public synchronized static SingletonObject3 getInstance(){
        if(null == instance){
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }




}
