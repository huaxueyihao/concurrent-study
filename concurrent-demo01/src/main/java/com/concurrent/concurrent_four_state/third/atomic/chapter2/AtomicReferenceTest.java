package com.concurrent.concurrent_four_state.third.atomic.chapter2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AtomicReferenceTest {

    public static void main(String[] args) {

        AtomicReference<Simple> atomicReference = new AtomicReference<>(new Simple("Alex",12));
        Simple simple = atomicReference.get();
        System.out.println(simple);

        boolean result = atomicReference.compareAndSet(new Simple("sdfs", 22), new Simple("sdfs", 234));
        System.out.println(result);

        JButton button = new JButton();
        Simple s= new Simple("test",12);
        final AtomicReference<Simple> atomicReference1 = new AtomicReference<>(new Simple("Alex",12));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // s = new Simple("test1",22); 编译不通过
                atomicReference1.set(new Simple("test2",33));


            }
        });


    }

    static class ObjectWrap<T>{

        private T t;
        private ObjectWrap(T t){
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


}
