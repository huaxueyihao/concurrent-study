package com.concurrent.practice_video.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 并行求和
 */
public class Demo7 {

    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(10, 20, 30, 40,50);
        Demo7 demo7 = new Demo7();
        int res = demo7.add(values);
        System.out.println("计算结果：" + res);

    }

    public int add(List<Integer> values) {
        values.parallelStream().forEach(System.out::println);
        return values.parallelStream().mapToInt(a -> a).sum();
    }


}
