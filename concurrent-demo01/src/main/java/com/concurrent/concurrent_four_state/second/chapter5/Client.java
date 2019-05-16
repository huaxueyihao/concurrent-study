package com.concurrent.concurrent_four_state.second.chapter5;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();

        User bj = new User("Baobao","Beiging",gate);
        User sh = new User("ShangLao","ShangHai",gate);
        User gz = new User("GuangLao","GuangZhou",gate);

        bj.start();
        sh.start();
        gz.start();

    }
}
