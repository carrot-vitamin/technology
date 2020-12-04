package com.project.java.technology.senior.batch.v2;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author za-yinshaobo at 2020/12/3 17:30
 */
public class MessagesItemProcessor implements ItemProcessor<User, Message> {

    @Override
    public Message process(User user) throws Exception {
        Message m = new Message();
        m.setContent("Hello "
                + user.getName() +
                ", please pay promptly at the end of this month.");

        return m;
    }
}
