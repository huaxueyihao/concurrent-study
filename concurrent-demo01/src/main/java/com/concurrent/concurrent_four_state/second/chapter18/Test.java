package com.concurrent.concurrent_four_state.second.chapter18;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Test {

    public static void main(String[] args) {

        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakeClientThread(activeObject, "Alice").start();
        new MakeClientThread(activeObject, "Bobby").start();

        new DisplayClientThread("Chris", activeObject).start();



    }
}
