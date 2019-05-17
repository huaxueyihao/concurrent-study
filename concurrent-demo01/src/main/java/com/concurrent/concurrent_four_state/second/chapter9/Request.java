package com.concurrent.concurrent_four_state.second.chapter9;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Request {

    final private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
