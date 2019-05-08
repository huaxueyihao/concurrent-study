package com.concurrent.concurrent_four_state.first.chapter6;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;


    public void execute(Runnable task){

        executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();

                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        };

        executeThread.start();
    }

    public void shutdown(long mills){
        long currentTime  = System.currentTimeMillis();
        while (!finished){
            if((System.currentTimeMillis() -currentTime)>= mills){
                System.out.println("任务超时");
                executeThread.interrupt();
                break;
            }

            try {
                Thread.sleep(1);
            }catch (InterruptedException e){
//                e.printStackTrace();
                System.out.println("执行线程被打断");
                break;
            }
        }

    }

}
