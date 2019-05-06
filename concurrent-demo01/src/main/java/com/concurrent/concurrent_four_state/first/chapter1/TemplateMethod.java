package com.concurrent.concurrent_four_state.first.chapter1;

/**
 * @Description: 模版方法
 * @Author: huaxueyihao
 * @Date:
 * @Version:
 **/
public abstract class TemplateMethod {

    public final void print(String message) {
        System.out.println("##################");
        wrapPrint(message);
        System.out.println("##################");
    }

    protected abstract void wrapPrint(String message);


    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        t1.print("Helle Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("$$$" + message + "$$$");
            }
        };
        t2.print("Helle Thread");

    }

}
