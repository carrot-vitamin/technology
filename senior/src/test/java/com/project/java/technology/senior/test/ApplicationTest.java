package com.project.java.technology.senior.test;

import com.project.java.technology.senior.service.strategy.Strategy;
import com.project.java.technology.senior.service.strategy.impl.StrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author za-yinshaobo
 * 2020/9/3 14:20
 */
@SpringBootTest
public class ApplicationTest {

    @Resource
    private StrategyFactory factory;

    @Test
    public void test() {
        Strategy strategy = this.factory.build("B");
        System.out.println(strategy.getClass().getName());
        double price = strategy.calcPrice(100);
        System.out.println(price);
    }
}
