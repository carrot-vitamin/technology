package com.project.java.technology.primary.design.strategy.example;

/**
 * @author ShaoBo Yin
 * @date 2020/10/19 16:33
 * 初级会员
 */
public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }
}
