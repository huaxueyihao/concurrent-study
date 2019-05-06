package com.concurrent.concurrent_four_state.first.chapter1;

/**
 * @Description:
 * @Author: maoruiquan
 * @Date:
 * @Version:
 **/
public class TryConcurrency {


    public static void main(String[] args) {

//        readFromDataBase();
//        writeDataToFile();

        new Thread("READ-Thread"){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread("WRITE—Thread"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();

    }



    private static void readFromDataBase() {

        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 10L);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");

    }

    private static void writeDataToFile() {
        try {
            println("Begin read data from db.");
            Thread.sleep(2000 * 10L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");

    }

    private static void println(String message) {
        System.out.println(message);
    }


}
