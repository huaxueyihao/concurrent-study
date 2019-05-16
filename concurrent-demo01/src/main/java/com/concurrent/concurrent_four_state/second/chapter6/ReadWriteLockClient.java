package com.concurrent.concurrent_four_state.second.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/

/**
 * ReadWriteLock design patterns
 * Reader-Writer design patterns
 *
 */
public class ReadWriteLockClient {

    public static void main(String[] args) {

        final SharedData sharedData = new SharedData(10);
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();

        new WriterWorker(sharedData,"reeredfd").start();
        new WriterWorker(sharedData,"WSDFSDF").start();


    }

}
