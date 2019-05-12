package com.concurrent.concurrent_four_state.first.chapter11;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Test2 {

    public void test(){

        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e->!e.isNativeMethod())
                .forEach(e-> Optional.of(e.getClassName()+":"+e.getMethodName()+":"+e.getLineNumber()).ifPresent(System.out::println));

    }
}
