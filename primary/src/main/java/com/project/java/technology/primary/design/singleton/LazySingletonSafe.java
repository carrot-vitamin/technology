package com.project.java.technology.primary.design.singleton;

/**
 * @ClassName: LazySingletonSafe
 * @Description: 懒汉模式（线程安全的，但是效率极低，极大多数情况不需要同步）
 * @author: ShaoBo Yin
 * @date: 2019/3/1 10:10 AM
 */
public class LazySingletonSafe {

    private LazySingletonSafe() {}

    private static LazySingletonSafe instance;

    public static synchronized LazySingletonSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonSafe();
        }
        return instance;
    }
}
