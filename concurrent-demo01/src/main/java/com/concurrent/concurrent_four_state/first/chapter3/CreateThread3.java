package com.concurrent.concurrent_four_state.first.chapter3;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public class CreateThread3 {

    private int i = 0;

    private byte[] bytes = new byte[1024];

    private static int counter = 0;


    // JVM will create a thread named "main"
    public static void main(String[] args) {
        // create a JVM statck

        // int j = 0;
        // int[] arr = new int[1024];

        try {
            add(0);
        }catch (Error e){
            System.out.println(counter);
            e.printStackTrace();

        }



    }

    private static void add(int i) {
        counter++;
        add(i + 1);
    }


}
