package com.concurrent.concurrent_four_state.second.chapter4.demo01;

import java.util.List;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id = " + id);
                    Thread.sleep(1000);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));

                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());

    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {

        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data chanaged and state is [" + event.getState() + "]");
            if(event.getCause() != null){
                System.out.println("The runnable ["+event.getThread().getName()+"] execute failure");
                event.getCause().printStackTrace();
            }
        }
    }
}
