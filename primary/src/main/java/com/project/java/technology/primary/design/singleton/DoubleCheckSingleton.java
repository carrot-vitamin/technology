package com.project.java.technology.primary.design.singleton;

/**
 * @ClassName: DoubleLockSingleton
 * @Description: 双重校验锁模式
 * @author: ShaoBo Yin
 * @date: 2019/3/1 10:54 AM
 */
public class DoubleCheckSingleton {

    private DoubleCheckSingleton() {}

    private static volatile DoubleCheckSingleton instance;

    public static DoubleCheckSingleton getInstance() {
        //第一次校验
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                //第二次校验
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
