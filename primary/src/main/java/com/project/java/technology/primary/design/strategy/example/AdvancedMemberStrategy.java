package com.project.java.technology.primary.design.strategy.example;

/**
 * @author ShaoBo Yin
 * @date 2020/10/19 16:33
 * 高级会员
 */
public class AdvancedMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}
