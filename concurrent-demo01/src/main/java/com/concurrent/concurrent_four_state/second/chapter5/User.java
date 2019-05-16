package com.concurrent.concurrent_four_state.second.chapter5;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class User extends Thread {

    private final String myName;

    private final String myAddress;

    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName + " BEEGIN");
        while (true) {
            this.gate.pass(myName, myAddress);
        }
    }
}
