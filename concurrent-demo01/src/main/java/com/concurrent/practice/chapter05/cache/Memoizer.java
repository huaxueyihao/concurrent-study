package com.concurrent.practice.chapter05.cache;

import java.util.Map;
import java.util.concurrent.*;


public class Memoizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
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
            cache.putIfAbsent(arg,ft);
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
