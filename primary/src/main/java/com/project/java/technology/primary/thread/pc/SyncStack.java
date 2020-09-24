package com.project.java.technology.primary.thread.pc;

/**
 * @author za-yinshaobo
 * 2020/9/24 15:06
 */
public class SyncStack {

    private int size;

    private Apple [] apples;

    private int index = 0;

    public SyncStack(int size) {
        this.size = size;
        this.apples = new Apple[size];
    }

    public synchronized void push(Apple apple) {
        while (index == size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        apples[index] = apple;
        index ++;
        System.out.println("生产者生产... ... : " + apple);
    }

    public synchronized Apple pop() {
        while (index == 0) {
            try {
                System.out.println("消费者进入消费... ...");
                Thread.sleep(1000);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index --;
        Apple apple = apples[index];
        System.out.println("消费者消费... ... : " + apple);
        return apple;
    }
}
