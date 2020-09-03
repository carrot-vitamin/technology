package com.project.java.technology.primary.design.singleton;

/**
 * @ClassName: HungrySingleton
 * @Description: 饿汉模式，基于ClassLoader机制在类装载时实例化，避免了多线程的问题。但是无法避免其他方式导致类重新装载，此时instance无法达到lazy loading效果
 * @author: ShaoBo Yin
 * @date: 2019/3/1 10:14 AM
 */
public class HungrySingleton {

    private HungrySingleton() {}

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }
}

/**
 * 变种后的饿汉模式
 */
class HungrySingleton1 {

    private HungrySingleton1() {}

    private static HungrySingleton1 instance;

    static {
        instance = new HungrySingleton1();
    }

    public static HungrySingleton1 getInstance() {
        return instance;
    }
}
