package com.project.java.technology.senior.service.strategy.v2.impl;

import com.project.java.technology.senior.service.strategy.v2.Strategy2;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 17:00
 */
public abstract class AbsStrategy2 implements Strategy2 {

    @Resource
    private StrategyFactory2 factory;

    @PostConstruct
    public void init() {
        this.factory.register(this);
    }
}
