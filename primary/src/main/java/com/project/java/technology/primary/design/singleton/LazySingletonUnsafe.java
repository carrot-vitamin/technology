package com.project.java.technology.primary.design.singleton;

/**
 * @ClassName: LazySingletonUnsafe
 * @Description: 懒汉模式（线程不安全的）
 * @author: ShaoBo Yin
 * @date: 2019/3/1 9:46 AM
 */
public class LazySingletonUnsafe {

    private LazySingletonUnsafe() {
    }

    private static LazySingletonUnsafe instance;

    public static LazySingletonUnsafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonUnsafe();
        }
        return instance;
    }
}
