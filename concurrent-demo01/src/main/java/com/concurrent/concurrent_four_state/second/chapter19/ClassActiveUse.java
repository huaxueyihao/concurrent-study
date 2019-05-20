package com.concurrent.concurrent_four_state.second.chapter19;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class ClassActiveUse {

    public static void main(String[] args) throws ClassNotFoundException {
//        new Obj();
//        System.out.println(I.a);
//        System.out.println(Obj.salary);

//        Obj.printSalary();
//        Class.forName("com.concurrent.concurrent_four_state.second.chapter19.Obj");

//        System.out.println(Child.age);

//        System.out.println(Child.salary);

//        Obj[] arrays = new Obj[10];

        System.out.println(Obj.salary);


    }



}

class Obj {

    public static final long salary = 100000L;

    static {
        System.out.println("Obj 被初始化");
    }

    public static void printSalary(){
        System.out.println(salary);
    }
}

class Child extends Obj{
    public static long age = 32;

    static {
        System.out.println("Child 被初始化");
    }


}

interface I{
    int a = 10;
}

// 访问磨具个类或接口的静态变量，或者对该金泰变量进行赋值操作
// 1.对某个类的静态变量来那个进行读写，初始化class
// 2.对接口中静态变量进行读取，初始化interface
// 3.调用静态方法
// 4.反射某个类
// 5.初始化子类,父类先初始化，
// 6.通过数组引用 ,定义数组，不会初始化

