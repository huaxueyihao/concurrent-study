package com.concurrent.practice.chapter05.cache;

public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;

}
