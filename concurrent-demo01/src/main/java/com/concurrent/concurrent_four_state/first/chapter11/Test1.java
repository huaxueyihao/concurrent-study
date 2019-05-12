package com.concurrent.concurrent_four_state.first.chapter11;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Test1 {

    private Test2 test2 = new Test2();

    public void test(){
        test2.test();
    }

    public static void main(String[] args) {
        new Test1().test();
    }

}
