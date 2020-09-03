package com.project.java.technology.senior.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.project.java.technology.core.model.Constants.HOST_VIRTUAL;

/**
 * @author yin
 * @desc TopicProducer
 * @date 2018/12/1 22:23
 */
public class TopicProducer {

    /**
     * ActiveMq 的默认用户名
     */
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    /**
     * ActiveMq 的默认登录密码
     */
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    /**
     * ActiveMQ 的链接地址
     */
    private static final String BROKEN_URL = "tcp://" + HOST_VIRTUAL + ":61616";

    public static void main(String[] args) throws Exception {
        //创建一个链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
        //从工厂中创建一个链接
        Connection connection = connectionFactory.createConnection();
        //开启链接
        connection.start();
        //创建一个事务（这里通过参数可以设置事务的级别）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建Topic，订阅该Topic的listener都能接收到该消息
        Topic topic = session.createTopic("MessageTopic");
        MessageProducer messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("hello, everyone!");
        messageProducer.send(textMessage);

        messageProducer.close();
        session.close();
        connection.close();
    }

}
