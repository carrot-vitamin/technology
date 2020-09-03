package com.project.java.technology.senior.activemq.p2p;

/*
* Point to Point (P2P模型)
* 多个消息的生产者和消费者都可以注册到同一个消息队列，当生产者发送一条消息之后，只有其中一个消息
* 消费者会接收到消息，而不是所有的消费者都会收到该消息。
* */