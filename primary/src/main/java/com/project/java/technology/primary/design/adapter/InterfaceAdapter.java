package com.project.java.technology.primary.design.adapter;

/**
 * @ClassName: InterfaceAdapter
 * @Description: 接口适配器模式
 * 适用情况：当存在这样一个接口，其中定义了N多的方法，而我们现在却只想使用其中的一个到几个方法，如果我们直接实现接口，那么我们要对所有的方法进行实现，哪怕我们仅仅是对不需要的方法进行置空（只写一对大括号，不做具体方法实现）也会导致这个类变得臃肿，调用也不方便，这时我们可以使用一个抽象类作为中间件，即适配器
 * @author: ShaoBo Yin
 * @date: 2019/3/7 5:25 PM
 */
public class InterfaceAdapter extends B {

    @Override
    public void a() {
        super.a();
    }
}

interface A {
    void a();
    void b();
    void c();
}

abstract class B implements A {
    @Override
    public void a() {

    }

    @Override
    public void b() {

    }

    @Override
    public void c() {

    }
}