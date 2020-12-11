package com.project.java.technology.primary.design.strategy.example;

/**
 * @author ShaoBo Yin
 * @date 2020/10/19 16:36
 */
public class PriceClient {

    public static void main(String[] args) {
        //选择并创建需要使用的策略对象
        MemberStrategy strategy = new AdvancedMemberStrategy();
        //创建环境
        Price price = new Price(strategy);
        //计算价格
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }
}
