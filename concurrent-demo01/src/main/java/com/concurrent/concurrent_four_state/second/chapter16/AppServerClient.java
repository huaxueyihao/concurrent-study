package com.concurrent.concurrent_four_state.second.chapter16;

import java.io.IOException;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AppServerClient {

    public static void main(String[] args) throws InterruptedException, IOException {

        AppServer appServer = new AppServer(13345);
        appServer.start();

        Thread.sleep(15_000);
        appServer.shutdown();


    }
}
