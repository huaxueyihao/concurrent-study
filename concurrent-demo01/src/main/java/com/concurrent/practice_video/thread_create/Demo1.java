package com.concurrent.practice_video.thread_create;


/**
 * 1、空构造方法
 * 2、构造函数
 * 3、ThreadGroup：是一个树状结构的线程组
 * 4、垃圾回收线程：守护线程
 * 5、线程的中断：interrupt(),
 * interrupted()是静态方法，内部实现是调用的当前线程的isInterrupted()，并会重置当前线程的中断状态,
 * isInterrupted()：是调用盖房的对象所表示的那个线程的isInterrupted（），不会重置当前线程的中断状态
 * 6、stop()，只是让线程无限期等待，持有的锁没有释放
 *
 *
 * 注意：
 * （1）如果线程在调用 Object 类的 wait()、wait(long) 或 wait(long, int) 方法，或者该类的 join()、join(long)、join(long, int)、sleep(long) 或 sleep(long, int) 方法过程中受阻，则其中断状态将被清除，它还将收到一个InterruptedException异常。
 *      这个时候，我们可以通过捕获InterruptedException异常来终止线程的执行，具体可以通过return等退出或改变共享变量的值使其退出。
 * （2）如果该线程在可中断的通道上的 I/O 操作中受阻，则该通道将被关闭，该线程的中断状态将被设置并且该线程将收到一个 ClosedByInterruptException。这时候处理方法一样，只是捕获的异常不一样而已。
 */
public class Demo1 extends Thread {

    public Demo1(String name) {
        super(name);
    }

    @Override
    public void run() {

        while (!isInterrupted()) {
            System.out.println(getName() + "线程执行了..." + isInterrupted());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void main(String[] args) {

        Demo1 demo1 = new Demo1("first-thread");
        Demo1 demo2 = new Demo1("sencond-thread");

        demo1.start();
        demo2.start();

        demo1.interrupt();

    }

}
