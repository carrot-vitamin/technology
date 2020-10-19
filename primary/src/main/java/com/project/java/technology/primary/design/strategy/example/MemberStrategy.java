package com.project.java.technology.primary.design.strategy.example;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:33
 * 抽象折扣类
 */
public interface MemberStrategy {

    /**
     * 计算图书的价格
     * @param booksPrice
     * @return
     */
    double calcPrice(double booksPrice);
}
