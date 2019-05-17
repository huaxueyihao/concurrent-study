package com.concurrent.concurrent_four_state.second.chapter9;

import static sun.jvm.hotspot.runtime.PerfMemory.start;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        final RequestQueue requestQueue = new RequestQueue();

        new ClientThread(requestQueue, "Alex").start();
        ServerThread server = new ServerThread(requestQueue);
        server.start();
//        server.join();
        Thread.sleep(10000);
        server.close();



    }
}
