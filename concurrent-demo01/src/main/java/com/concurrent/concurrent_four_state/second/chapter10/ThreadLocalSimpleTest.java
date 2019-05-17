package com.concurrent.concurrent_four_state.second.chapter10;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };

    public static void main(String[] args) throws InterruptedException {

//        threadLocal.set("Alex");
        Thread.sleep(1000);
        String value= threadLocal.get();
        System.out.println(value);


    }
}
