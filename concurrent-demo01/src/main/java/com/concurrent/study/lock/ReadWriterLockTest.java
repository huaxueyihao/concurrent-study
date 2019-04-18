package com.concurrent.study.lock;

import java.util.Random;

/**
 * 读写线程同时在执行，存在线程安全问题
 */
public class ReadWriterLockTest {


    static class ReadWriteMethod {

        private Object data = null;

        public void getData() {
            try {
                System.out.println("准备读取数据");
                Thread.sleep(1000);
                System.out.println("读取到数据为-----------" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }

        public void setData(Object data){
            try {
                System.out.println("准备写入数据");
                Thread.sleep(500);
                this.data = data;
                System.out.println("写入的数据为-----------" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }


    public static void main(String[] args) {

        final ReadWriteMethod rwm = new ReadWriteMethod();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rwm.setData(new Random().nextInt(10000));
                }
            },"t2").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    rwm.getData();
                }
            },"t1").start();
        }

    }


}
