package com.concurrent.concurrent_four_state.second.chapter1;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SingletonObject4 {

    private static SingletonObject4 instance ;

    private SingletonObject4(){

    }

    // double check
    public  static SingletonObject4 getInstance(){
        if(null == instance){
            synchronized (SingletonObject4.class){
                if(null == instance){
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }




}
