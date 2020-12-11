package com.project.java.technology.primary.thread.pc;

/**
 * @author ShaoBo Yin
 * 2020/9/24 15:17
 */
public class Main {

    public static void main(String[] args) {
        SyncStack syncStack = new SyncStack(2);
        Producer producer = new Producer(syncStack);
        Consumer consumer = new Consumer(syncStack);
        producer.start();
        consumer.start();
    }
}
