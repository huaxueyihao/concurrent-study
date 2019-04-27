package com.concurrent.practice_video.singleton;

public class Singleton2 {

    private Singleton2(){}

    private static Singleton2 instance;

    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            // 线程0走进来，还未创建,线程1进来了
            instance = new Singleton2();
        }
        return instance;
    }

}
