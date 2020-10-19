package com.project.java.technology.senior.service.strategy.v2.impl;

import com.project.java.technology.senior.service.strategy.v2.ITest;
import org.springframework.stereotype.Component;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:48
 */
@Component
public class StrategyC extends AbsStrategy2 {

    @Override
    public void operate(ITest iTest) {
        TestA testA = (TestA) iTest;
        System.out.println(testA);
    }

    @Override
    public String type() {
        return "A";
    }
}
