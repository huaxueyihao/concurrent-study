package com.concurrent.concurrent_four_state.first.chapter3;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class CreateThread5 {

    private static int counter = 1;

    public static void main(String[] args) {


        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter++;
                new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Error e) {

        }

        System.out.println("Total created thread nums=>" + counter);


    }

}
