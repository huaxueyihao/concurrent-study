package com.concurrent.practice.chapter05.cache;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

/**
 * 保证了线程的安全性，却带来了伸缩性问题，每次只能有一个线程执行compute方法，多个线程环境中，效率低下
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer1<A,V> implements Computable<A,V> {

    @GuardedBy("this")
    private final Map<A,V> cache = new HashMap<>();

    private final Computable<A,V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
