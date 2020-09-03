package com.project.java.technology.senior.activemq.p2p;

import com.project.java.technology.senior.activemq.model.ActiveMqMsg;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.project.java.technology.core.model.Constants.HOST_VIRTUAL;

/**
 * @author yin
 * @desc Producer
 * @date 2018/12/1 22:23
 */
public class Producer {

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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
        //为了避免收到恶意代码，apache引入了安全机制，只允许指定的包里的对象能够被传输
        connectionFactory.setTrustAllPackages(true);
        //从工厂中创建一个链接
        Connection connection = connectionFactory.createConnection();
        //开启链接
        connection.start();
        //创建一个事务（这里通过参数可以设置事务的级别）
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //创建queue，消息发送者会向这个queue发送消息
        Destination destination = session.createQueue("MessageQueue");
        MessageProducer messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        ActiveMqMsg msg = new ActiveMqMsg();
        Date date = new Date();
        msg.setCreateTime(date);
        msg.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        ObjectMessage message = session.createObjectMessage(msg);
        messageProducer.send(message);

        session.commit();

        messageProducer.close();
        session.close();
        connection.close();
    }

}
