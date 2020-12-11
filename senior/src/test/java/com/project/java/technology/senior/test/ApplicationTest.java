package com.project.java.technology.senior.test;

import com.project.java.technology.senior.service.strategy.Strategy;
import com.project.java.technology.senior.service.strategy.impl.StrategyFactory;
import com.project.java.technology.senior.service.strategy.v2.impl.StrategyFactory2;
import com.project.java.technology.senior.service.strategy.v2.impl.TestA;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ShaoBo Yin
 * 2020/9/3 14:20
 */
@SpringBootTest
public class ApplicationTest {

    @Resource
    private StrategyFactory factory;

    @Resource
    private StrategyFactory2 factory2;

    @Test
    public void test() {
        Strategy strategy = this.factory.build("B");
        System.out.println(strategy.getClass().getName());
        double price = strategy.calcPrice(100);
        System.out.println(price);

        System.out.println("----------------------------");

        this.factory2.build("A").operate(new TestA());
    }
}
