package com.project.java.technology.senior.service.strategy.v2.impl;

import com.project.java.technology.senior.service.strategy.v2.Strategy2;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:57
 */
@Component
public class StrategyFactory2 {

    private Map<String, Strategy2> map = new HashMap<>(2);

    public void register(Strategy2 strategy) {
        this.map.put(strategy.type(), strategy);
    }

    public Strategy2 build(String type) {
        return map.get(type);
    }
}
