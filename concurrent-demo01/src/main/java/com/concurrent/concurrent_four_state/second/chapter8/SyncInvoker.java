package com.concurrent.concurrent_four_state.second.chapter8;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/

/**
 * Future ->代表未来的一个凭据
 * FutureTask ->将你的调用逻辑进行了隔离
 * FutureService->桥接Future和FutureTask
 *
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {

//        String result = get();
//        System.out.println(result);

        FutureService futureService = new FutureService();
        futureService.submit(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);

        System.out.println("============================");
        System.out.println(" do other thing ");
        Thread.sleep(1000);
        System.out.println("============================");



    }

    private static String get() throws InterruptedException {
        Thread.sleep(10000L);
        return "FINISH";
    }

}
