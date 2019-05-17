package com.concurrent.concurrent_four_state.second.chapter11;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class QueryFromDBAction {

    public void execute(){

        try {
            Thread.sleep(1000L);
            String name = "Alex"+ Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}



