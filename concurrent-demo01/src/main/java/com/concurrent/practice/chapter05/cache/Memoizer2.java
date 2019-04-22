package com.concurrent.practice.chapter05.cache;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程可以并发的使用它。
 * 不足：当两个线程同时调用compute时存在一个漏洞，可嗯呢该会导致计算得到相同的值。
 * 在使用memoization的情况下，这只会带来低效，因为缓存的作用是为了避免相同的值被计算多次。
 *
 * 问题：若某个线程启动了一个开销很大的计算，而其他线程并不知道这个计算正在进行，那么很可能会重复这个计算
 * 我们希望通过某种方法来表示"线程X正在计算f(27)，"，这样当另一个线程查找f(27)时，它能够知道最搞笑的方法是等待线程X计算结束，
 * 然后查询缓存"f(27)的结果是多少"
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer2<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new ConcurrentHashMap<>();

    private final Computable<A,V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public  V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
