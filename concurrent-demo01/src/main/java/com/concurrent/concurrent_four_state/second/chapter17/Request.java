package com.concurrent.concurrent_four_state.second.chapter17;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Request {

    private final String name;

    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }


    @Override
    public String toString() {
        return "Request{" +

                " number=" + number + '\n' +
                "name='" + name +
                '}';
    }
}
