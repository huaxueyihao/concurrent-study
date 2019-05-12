### 1 线程介绍
    
    线程：线程是一个实体，是cpu调度和分派的基本单位，它是比进程更小的能独立运行的基本单位
    进程：具有一定队里的程序关数据集合上的一次运行活动，进程是系统进行资源分配和调度的一个独立单位
    
### 2 线程创建启动线程
    
    Thread t = new Thread(){
        public void run(){
            // run code
        }
    };
    t.start();

### 3 线程生命周期

    1、创建线程(Thread t = new Thread())
    2、启动线程(t.start())，可执行状态(runnable)
    3、cpu调度，拿到执行权(running)
    4、被阻塞（blocked），running状态调用sleep，wait等进入阻塞状态，blocked状态必须进过runnable状态才能进入running状态
    5、结束（terminated）（2、3、4都有可能进入死亡状态）
    
    ![blockchain](https://github.com/huaxueyihao/concurrent-study/blob/master/concurrent-demo01/src/main/resources/first-image/Jietu20190506-212547.jpg "线程生命周期")
    
    小结：
    1、main函数是一个线程，
    2、创建线程，复写run方法，调用start启动
    3、JVM启动后，实际上至少由多个线程，至少有一个非守护线程
    4、当调用一个线程start方法的时候，此时至少有两个线程，一个是调用你的线程，还有一个是组线程
    5、线程生命周期：new、runnable、running、block、termate

### 4 Runnable接口介绍

    new Thread(new Runnable(){
        
        @override
        public void run(){
            // run
        }
    }).start();
    策略模式

### 5 Thread API详细介绍
    1、守护线程可以做心跳检测
    2、join()
    
### 6 线程同步、锁技术

### 7 如何优先的停止线程

### 8 线程间通信

    sleep()和wait()区别
    1、sleep是Thread方法，wait是Object的方法
    2、sleep不会释放锁，wait会释放锁，并且将线程加入到等待对列中
    3、sleep不依赖锁，wait依赖锁
    4、sleep不需要唤醒，wait需要唤醒
    
### 9 线程组详细介绍
    
### 10 线程池原理以及实现一个简单的线程池
### 11 线程异常捕获以及线程对战信息详细讲解
### 12 FIFO对列预计多线程环境下的运行
### 13 BooleanLock锁实现
### 14 常用设计模式在多线程下的使用 





