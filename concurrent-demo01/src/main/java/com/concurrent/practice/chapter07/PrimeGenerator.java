package com.concurrent.practice.chapter07;

import net.jcip.annotations.GuardedBy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通过设置取消标志
 */
public class PrimeGenerator implements Runnable {


    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p = p.nextProbablePrime();

            System.out.println(Thread.currentThread().getName()+":素数="+p);
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        Thread thread = new Thread(primeGenerator);
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("取消生成素数线程");
        primeGenerator.cancel();
        List<BigInteger> bigIntegers = primeGenerator.get();
        System.out.println(Arrays.toString(bigIntegers.toArray()));


    }
}
