package com.concurrent.concurrent_four_state.second.chapter1;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SingletonObject6 {

    /**
     * 1、类装载
     * 2、初始化
     * 3、
     **/
    private SingletonObject6() {

    }


    /**
     * 调用 getInstance的时候才会加载InstanceHolder
     **/
    private static class InstanceHolder {
        private final static SingletonObject6 instance = new SingletonObject6();

    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }


}
