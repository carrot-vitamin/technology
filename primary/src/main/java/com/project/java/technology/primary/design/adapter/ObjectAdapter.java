package com.project.java.technology.primary.design.adapter;


import com.project.java.technology.primary.design.adapter.service.Ps2;
import com.project.java.technology.primary.design.adapter.service.Usb;

/**
 * @ClassName: ObjectAdapter
 * @Description: 对象适配器模式，通过组合来实现适配器功能
 * 适用情况：当我们要访问的接口A中没有我们想要的方法 ，却在另一个接口B中发现了合适的方法，我们又不能改变访问接口A
 * @author: ShaoBo Yin
 * @date: 2019/3/7 5:22 PM
 */
public class ObjectAdapter implements Ps2 {

    private Usb usb;

    public ObjectAdapter(Usb usb) {
        this.usb = usb;
    }

    @Override
    public void isPs2() {
        this.usb.isUsb();
    }
}
