package com.concurrent.concurrent_four_state.first.chapter10;

/**
 * @Description: 注入hook
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExitCapture {

    public static void main(String[] args) {

        // 生命周期钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The application will be exit.");
            notifyAndRelease();

        }));

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000L);
                System.out.println("I am working");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//
//            i++;
//            if (i > 20) {
//                throw new RuntimeException("Error ");
//            }
        }
    }

    private static void notifyAndRelease() {
        System.out.println("notify to the admin");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I will release resource(socket.file.connection)");

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Release and notify done");


    }


}
