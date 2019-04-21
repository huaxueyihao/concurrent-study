package com.concurrent.practice.chapter05;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(2000);
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file) {
        System.out.println(Thread.currentThread().getName() + "长度：" + queue.size() + ",消费:" + file.getName());
    }
}
