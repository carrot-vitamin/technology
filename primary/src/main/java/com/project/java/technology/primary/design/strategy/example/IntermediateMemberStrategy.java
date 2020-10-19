package com.project.java.technology.primary.design.strategy.example;

/**
 * @author za-yinshaobo
 * @date 2020/10/19 16:33
 * 中级会员
 */
public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }
}
