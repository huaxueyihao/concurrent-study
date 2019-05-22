package com.concurrent.concurrent_four_state.second.chapter20;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class LoaderClass {

    public static void main(String[] args) {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        MyObject myObject3 = new MyObject();
        MyObject myObject4 = new MyObject();

        System.out.println(myObject1.getClass() == myObject2.getClass());
        System.out.println(myObject1.getClass() == myObject3.getClass());
        System.out.println(myObject1.getClass() == myObject4.getClass());

    }


}

class MyObject {

    public static int x = 10;

}

/**
 * 1、
 * 2、加载 查找，加载二进制数据
 * 3、链接
 *    3.1 验证 VeryError，元数据验证（是否有父类、父类是不是允许继承、
 *    是否实现了抽象方法、是否覆盖了父类的final字段、其他的语义验证），
 *    字节码验证，符号验证，
 *    3.2 准备
 *    3.3 解析（符号引用变成直接引用）
 * 4、初始化阶段
 */
