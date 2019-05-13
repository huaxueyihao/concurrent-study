package com.concurrent.concurrent_four_state.second.chapter1;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SingletonObject2 {

    private static  SingletonObject2 instance ;

    private SingletonObject2(){

    }

    public static SingletonObject2 getInstance(){
        if(null == instance){
            instance = new SingletonObject2();
        }
        return SingletonObject2.instance;
    }




}
