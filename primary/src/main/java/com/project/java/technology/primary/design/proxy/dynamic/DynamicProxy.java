package com.project.java.technology.primary.design.proxy.dynamic;


import com.project.java.technology.primary.design.proxy.statics.ISinger;
import com.project.java.technology.primary.design.proxy.statics.Singer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: DynamicProxy
 * @Description:
 * @author: ShaoBo Yin
 * @date: 2019/3/1 5:32 PM
 */
public class DynamicProxy {

    public static void main(String[] args) {
        Singer singer = new Singer();
        ISinger singerProxy = (ISinger) Proxy.newProxyInstance(singer.getClass().getClassLoader(), singer.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("hello!");
                Object returnValue = method.invoke(singer, args);
                System.out.println("Thanks!");
                return returnValue;
            }
        });
        singerProxy.sing();
    }
}
