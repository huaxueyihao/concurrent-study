package com.concurrent.concurrent_four_state.second.chapter18;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class RealResult implements Result {

    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object getResultValue() {
        return resultValue;
    }
}
