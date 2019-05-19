package com.concurrent.concurrent_four_state.second.chapter18;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class DisplayStringRequest extends MethodRequest {

    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
