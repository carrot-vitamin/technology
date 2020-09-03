package com.project.java.technology.senior.soa.rpc.tcp.service.impl;


import com.project.java.technology.senior.soa.rpc.tcp.service.ISayHelloService;

/**
 * @author yin
 * @desc SayHelloServiceImpl
 * @date 2018/12/19 22:27
 */
public class SayHelloServiceImpl implements ISayHelloService {
    @Override
    public String sayHello(String name) {
        return "hello, " + name + "!";
    }
}
