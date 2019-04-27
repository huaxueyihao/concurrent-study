package com.concurrent.practice_video.singleton;

/**
 * 饿汉式:没有线程安全
 */
public class Singleton {

    // 私有化构造方法
    private Singleton(){}

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    };


}
