package com.project.java.technology.senior.activemq.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.project.java.technology.core.model.Constants.HOST_VIRTUAL;

/**
 * @author yin
 * @desc Consumer
 * @date 2018/12/1 22:44
 */
public class Consumer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String BROKEN_URL = "tcp://" + HOST_VIRTUAL + ":61616";

    public static void main(String[] args) throws Exception {
        //创建一个链接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
        connectionFactory.setTrustAllPackages(true);
        //从工厂中创建一个链接
        Connection connection = connectionFactory.createConnection();
        //开启链接
        connection.start();
        //创建一个事务（这里通过参数可以设置事务的级别）
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //创建queue，消息发送者会向这个queue发送消息
        Destination destination = session.createQueue("MessageQueue");
        MessageConsumer messageConsumer = session.createConsumer(destination);

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                try {
                    System.out.println("收到消息：" + objectMessage.getObject().toString());
                    //提交事务，消费消息
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                    try {
                        messageConsumer.close();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //取出消息
//                while (true) {
//                    try {
//                        ObjectMessage message = (ObjectMessage) messageConsumer.receive(1000);
//                        if (message != null) {
//                            System.out.println(message.getObject().toString());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

    }
}