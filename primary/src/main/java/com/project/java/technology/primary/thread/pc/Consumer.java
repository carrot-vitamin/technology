package com.project.java.technology.primary.thread.pc;

/**
 * @author ShaoBo Yin
 * 2020/9/24 15:13
 */
public class Consumer extends Thread {

    private SyncStack syncStack;

    public Consumer(SyncStack syncStack) {
        this.syncStack = syncStack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i ++) {
            this.syncStack.pop();
        }
    }
}
