package com.concurrent.practice_video.singleton;

public class Singleton2 {

    private Singleton2(){}

    private static volatile Singleton2 instance;

    /**
     * 偏向锁
     * 轻量级
     * 自旋：消耗资源
     * @return
     */
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            // 线程0走进来，还未创建,线程1进来了
            synchronized (Singleton2.class){
                if(instance == null){
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

}
