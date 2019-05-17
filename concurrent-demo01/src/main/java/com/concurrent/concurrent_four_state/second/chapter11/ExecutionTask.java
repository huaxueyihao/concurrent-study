package com.concurrent.concurrent_four_state.second.chapter11;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ExecutionTask implements Runnable {


    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();


    @Override
    public void run() {

        Context context = ActionContext.getActionContext().getContext();
        queryFromDBAction.execute();
        System.out.println("The name query successful.");
        httpAction.execute();
        System.out.println("The card id query successful.");
        System.out.println("The Name is " + context.getCardId() + " and CardId " + context.getCardId());


    }
}
