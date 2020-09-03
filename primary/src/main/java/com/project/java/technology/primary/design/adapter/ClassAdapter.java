package com.project.java.technology.primary.design.adapter;


import com.project.java.technology.primary.design.adapter.service.Ps2;
import com.project.java.technology.primary.design.adapter.service.impl.UsbImpl;

/**
 * @ClassName: ClassAdapter
 * @Description: 类适配器模式，通过继承来实现适配器功能
 * 适用情况：当我们要访问的接口A中没有我们想要的方法 ，却在另一个接口B中发现了合适的方法，我们又不能改变访问接口A
 * @author: ShaoBo Yin
 * @date: 2019/3/7 5:18 PM
 */
public class ClassAdapter extends UsbImpl implements Ps2 {

    @Override
    public void isPs2() {
        this.isUsb();
    }
}
