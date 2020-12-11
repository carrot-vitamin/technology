package com.project.java.technology.senior.batch.v2;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author ShaoBo Yin at 2020/12/3 17:28
 */
public class MessagesItemWriter implements ItemWriter<Message> {

    @Override
    public void write(List<? extends Message> list) throws Exception {
        System.out.println("write results... ...");
        for (Message m : list) {
            System.out.println(m.getContent());

        }
    }
}
