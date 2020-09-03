package com.project.java.technology.senior.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


/**
 * @ClassName: MyEventListener
 * @Description:
 * @author: yinshaobo
 * @date: 2019/4/10 3:19 PM
 */
@Slf4j
@Configuration
public class MyEventListener {

    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            log.info("start ok ... ...");
        }
    }
}
