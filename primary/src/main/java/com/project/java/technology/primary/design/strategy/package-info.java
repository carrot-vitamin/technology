/**
 * @author ShaoBo Yin
 * @date 2020/10/19 16:23
 * 策略模式属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。
 * 总结来说，一个接口多个实现，通过不同的策略来选择不同的实现
 * 涉及到三个角色：
 * 环境(Context)角色：持有一个Strategy的引用。
 * 抽象策略(Strategy)角色：这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。
 * 具体策略(ConcreteStrategy)角色：包装了相关的算法或行为。
 */
package com.project.java.technology.primary.design.strategy;