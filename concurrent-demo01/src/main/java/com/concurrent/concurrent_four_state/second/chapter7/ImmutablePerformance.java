package com.concurrent.concurrent_four_state.second.chapter7;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ImmutablePerformance {

    public static void main(String[] args) {
        long startTimestamp = System.currentTimeMillis();

        // 52053
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("Alex");

//        50340
//        ImmutableObj job = new ImmutableObj("job");

//        for (int i = 0; i < 10000000; i++) {
//            System.out.println(job.toString());
//        }

        Thread t1 = new Thread() {
            @Override
            public void run() {

            }
        };



        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));


    }


}

class ImmutableObj {

    private final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "[" + name + "]";
    }


}
