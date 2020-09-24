package com.project.java.technology.primary.thread.pc;

/**
 * @author za-yinshaobo
 * 2020/9/24 15:13
 */
public class Producer extends Thread {

    private SyncStack syncStack;

    public Producer(SyncStack syncStack) {
        this.syncStack = syncStack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i ++) {
            this.syncStack.push(new Apple(i + ""));
        }
    }
}
