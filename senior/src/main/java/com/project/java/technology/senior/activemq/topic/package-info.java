package com.project.java.technology.senior.activemq.topic;

/*
* Topic (发布/订阅模型)
* 消息发布者将消息投递给topic，消息的订阅者需要在相应的topic进行注册，与P2P模型不同的是，topic中的消息
* 可以被所有订阅了该topic的消费者接收。
* 订阅模式设置为持久订阅时，在消费者断开连接时，发布者将会为订阅者保留这段时间所产生的消息，当消息订阅者
* 重新链接是仍然可以获得这部分消息，而不至于丢失这部分消息。
*
* */