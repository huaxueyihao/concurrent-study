package com.concurrent.practice.chapter05.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 缺陷：仍然存在两个线程计算出相同值的漏洞。
 * 这个漏洞的发生概率要远小于Memoizer2中发生的概率，
 * 但由于compute方法中的if代码块仍有可能在同一个时间内调用compute来计算相同的值，
 * 即二者都没有在缓存中找到期望的值 。
 * @param <A>
 * @param <V>
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> future = cache.get(arg);
        if (future == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {

                    return c.compute(arg);
                }
            };

            FutureTask<V> ft = new FutureTask<>(eval);
            future = ft;
            cache.put(arg,ft);
            ft.run();
        }

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw launderThrowAble(e.getCause());
        }

    }

    private RuntimeException launderThrowAble(Throwable t) {
        if(t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof Error){
            throw (Error) t;
        }else
            throw new IllegalStateException("Not unchecked",t);
    }
}
