package com.concurrent.concurrent_four_state.second.chapter18;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/

/**
 * 对应ActiveObject的每一个方法
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }


    public abstract void execute();

}
