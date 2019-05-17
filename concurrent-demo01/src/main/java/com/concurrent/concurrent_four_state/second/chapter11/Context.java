package com.concurrent.concurrent_four_state.second.chapter11;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class Context {

    private String name;

    private String cardId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
