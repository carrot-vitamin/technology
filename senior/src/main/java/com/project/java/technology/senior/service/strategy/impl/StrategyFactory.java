package com.project.java.technology.senior.service.strategy.impl;

import com.project.java.technology.senior.service.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:57
 */
@Component
public class StrategyFactory {

    private Map<String, Strategy> map = new HashMap<>(2);

    public void register(Strategy strategy) {
        this.map.put(strategy.type(), strategy);
    }

    public Strategy build(String type) {
        return map.get(type);
    }
}
