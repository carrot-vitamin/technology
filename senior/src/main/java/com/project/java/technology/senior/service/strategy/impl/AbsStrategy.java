package com.project.java.technology.senior.service.strategy.impl;

import com.project.java.technology.senior.service.strategy.Strategy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author ShaoBo Yin
 * @date 2020/10/19 17:00
 */
public abstract class AbsStrategy implements Strategy {

    @Resource
    private StrategyFactory factory;

    @PostConstruct
    public void init() {
        this.factory.register(this);
    }
}
