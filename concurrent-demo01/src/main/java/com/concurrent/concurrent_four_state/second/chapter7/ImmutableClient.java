package com.concurrent.concurrent_four_state.second.chapter7;

import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ImmutableClient {

    public static void main(String[] args) {

        Person person = new Person("Alex", "GuanSu");
        IntStream.range(0,5).forEach(i-> {
            new UsePersonThread(person).start();
        });


    }


}
