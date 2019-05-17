package com.concurrent.concurrent_four_state.second.chapter11;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class QueryFromHttpAction {

    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "3444345454" + Thread.currentThread().getName();
    }


}
