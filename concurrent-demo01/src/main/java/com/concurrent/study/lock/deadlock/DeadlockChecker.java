package com.concurrent.study.lock.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockChecker {

    private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    final static Runnable deadlockCheck = new Runnable(){

        public void run() {
            while (true){
                long[] deadlockedThreads = mbean.findDeadlockedThreads();
                if(deadlockedThreads != null){
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreads);
                    for (Thread thread : Thread.getAllStackTraces().keySet()) {
                        for (int i = 0; i < threadInfos.length; i++) {
                            if(thread.getId() == threadInfos[i].getThreadId()){
                                thread.interrupt();
                            }
                        }
                    }

                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }

        }
    };

    public static void check(){
        Thread thread = new Thread(deadlockCheck);
        thread.setDaemon(true);
        thread.start();


    }


}
