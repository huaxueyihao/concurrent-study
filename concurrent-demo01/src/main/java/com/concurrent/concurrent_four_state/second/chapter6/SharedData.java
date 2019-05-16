package com.concurrent.concurrent_four_state.second.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SharedData {

    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }


    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();

        } finally {

            lock.readUnlock();

        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        } finally {
            lock.wirteUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
        }

    }


    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuf[i] = buffer[i];
        }
        slowly(50);
        return newBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
