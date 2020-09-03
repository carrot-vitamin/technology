package com.project.java.technology.primary.design.singleton;

/**
 * @ClassName: StaticInnerClassSingleton
 * @Description: 静态内部类模式，与饿汉模式不同，饿汉模式在类装载时就实例化instance，而该模式只有在调用了getInstance方法才会去装载内部类，可以到达lazy loading效果
 * @author: ShaoBo Yin
 * @date: 2019/3/1 10:35 AM
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {}

    private static class SingletonFactory {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingletonFactory.INSTANCE;
    }
}
