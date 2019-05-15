package com.concurrent.concurrent_four_state.second.chapter4.demo01;

import java.util.Arrays;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadLifeCycleClient {

    public static void main(String[] args) {

        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));


    }

}
