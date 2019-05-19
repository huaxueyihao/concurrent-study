package com.concurrent.concurrent_four_state.second.chapter18;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/

/**
 * 接受异步消息的主动对象
 */
public interface ActiveObject {

    Result makeString(int count,char fillChar);

    void displayString(String text);

}
