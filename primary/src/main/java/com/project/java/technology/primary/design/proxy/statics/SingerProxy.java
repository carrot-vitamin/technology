package com.project.java.technology.primary.design.proxy.statics;

/**
 * @ClassName: SingerProxy
 * @Description:
 * @author: ShaoBo Yin
 * @date: 2019/3/1 5:17 PM
 */
public class SingerProxy implements ISinger {

    private ISinger singer;

    public SingerProxy(ISinger singer) {
        this.singer = singer;
    }

    @Override
    public void sing() {
        System.out.println("Hello!");
        this.singer.sing();
        System.out.println("Thanks!");
    }
}
