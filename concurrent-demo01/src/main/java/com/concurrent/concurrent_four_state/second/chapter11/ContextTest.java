package com.concurrent.concurrent_four_state.second.chapter11;

import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ContextTest {

    public static void main(String[] args) {


        IntStream.range(1, 5).forEach(i -> {
            new Thread(new ExecutionTask()).start();
        });


    }
}
