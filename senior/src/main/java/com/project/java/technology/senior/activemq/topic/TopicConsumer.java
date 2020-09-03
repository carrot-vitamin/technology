package com.project.java.technology.senior.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.project.java.technology.core.model.Constants.HOST_VIRTUAL;

/**
 * @author yin
 * @desc TopicConsumer
 * @date 2018/12/1 22:44
 */
public class TopicConsumer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

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

        Topic topic = session.createTopic("MessageTopic");
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //订阅Topic
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("收到消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
