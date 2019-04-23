package com.concurrent.practice.chapter07;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BrokenPrimeProducer extends Thread {


    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            BigInteger p = BigInteger.ONE;


            while (!cancelled) {
                p = p.nextProbablePrime();
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ":生产的素数=" + p);
                queue.put(p);
            }
        } catch (InterruptedException e) {
        }
    }


    public void cancel() {
        cancelled = true;
    }

    public void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = queue;
        BrokenPrimeProducer brokenPrimeProducer = new BrokenPrimeProducer(primes);
        brokenPrimeProducer.start();

        try {
            while (needMorePrimes()) {
                Thread.sleep(250);
                consume(primes.take());
            }
        } finally {
            brokenPrimeProducer.cancel();
        }


    }

    private void consume(BigInteger prime) {
        System.out.println(Thread.currentThread().getName() + ":消费的素数=" + prime);
    }

    private boolean needMorePrimes() {
//        Random random = new Random();
//        int p = random.nextInt(1000);
//        if (p % 2 == 0) {
//            System.out.println("不需要素数了");
//            return true;
//        }
        return true;
    }


    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(20);
        BrokenPrimeProducer brokenPrimeProducer = new BrokenPrimeProducer(queue);
        brokenPrimeProducer.start();

        brokenPrimeProducer.consumePrimes();


//        Thread.sleep(3000);


    }


}
