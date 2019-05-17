package com.concurrent.concurrent_four_state.second.chapter7;

import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ImmutableTest {

    private final int age;
    private final String name;
    private final List<String> list;

    public ImmutableTest(int age, String name, List<String> list) {
        this.age = age;
        this.name = name;
        this.list = list;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

}
