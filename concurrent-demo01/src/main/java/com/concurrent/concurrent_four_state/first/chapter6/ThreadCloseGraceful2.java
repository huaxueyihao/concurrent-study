package com.concurrent.concurrent_four_state.first.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadCloseGraceful2 {

    private static class Worker extends Thread{


        @Override
        public void run() {
            while (true){
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    System.out.println("break");
//                    break;
//                    //e.printStackTrace();
//                }

                if(isInterrupted()){
                    System.out.println("break="+isInterrupted());
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();


    }






}
