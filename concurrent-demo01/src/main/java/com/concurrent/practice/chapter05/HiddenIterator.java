package com.concurrent.practice.chapter05;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏迭代器
 *
 * addTenThings可能会抛出ConcurrentModificationException
 *
 * 容器的hashCode和equals等方法也会间接地执行迭代操作，当容器作为另一个容器的元素或键值时，会出现这种情况
 * 同样、containsAll、removeAll和retainAll等方法，以及容器作为参数构造时。都会可能会抛出ConcurrentModificationException
 *
 */
public class HiddenIterator {

    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            add(random.nextInt());
        }
        System.out.println("DEBUG: added ten eleements to" + set);
    }

    public static void main(String[] args) {

        HiddenIterator hiddenIterator = new HiddenIterator();
        hiddenIterator.addTenThings();

    }

}
