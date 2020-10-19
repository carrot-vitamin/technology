package com.project.java.technology.senior.service.strategy.impl;

import org.springframework.stereotype.Component;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:48
 */
@Component
public class StrategyA extends AbsStrategy {

    @Override
    public double calcPrice(double price) {
        return 1;
    }

    @Override
    public String type() {
        return "A";
    }
}
