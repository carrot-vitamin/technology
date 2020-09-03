package com.project.java.technology.primary.design.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: ProxyFactory
 * @Description: Cglib子类代理工厂
 * @author: ShaoBo Yin
 * @date: 2019/3/7 1:49 PM
 */
public class ProxyFactory implements MethodInterceptor {

    /**
     * 维护目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象创建代理对象
     * @return
     */
    public Object getProxyInstance() {
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(this.target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Hello!");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("Thanks");
        return returnValue;
    }
}
