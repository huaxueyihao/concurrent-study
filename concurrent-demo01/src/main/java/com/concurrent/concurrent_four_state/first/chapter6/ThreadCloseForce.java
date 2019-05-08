package com.concurrent.concurrent_four_state.first.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();
        long start = System.currentTimeMillis();
        threadService.execute(()->{
            // load a very heavy resource
//            while (true){
//
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        threadService.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
}
