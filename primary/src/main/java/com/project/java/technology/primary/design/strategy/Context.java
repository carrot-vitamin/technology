package com.project.java.technology.primary.design.strategy;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:25
 * 环境角色类
 */
public class Context {

    /**
     * 持有一个具体策略的对象
     */
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略方法
     */
    public void contextInterface(){
        this.strategy.strategyInterface();
    }
}
