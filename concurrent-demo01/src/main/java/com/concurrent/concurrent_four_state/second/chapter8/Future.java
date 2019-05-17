package com.concurrent.concurrent_four_state.second.chapter8;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public interface Future<T> {

    T get() throws InterruptedException;

}
