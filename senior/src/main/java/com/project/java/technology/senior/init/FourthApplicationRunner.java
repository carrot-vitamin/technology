package com.project.java.technology.senior.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author ShaoBo Yin
 * 2020/9/2 10:24
 */
@Slf4j
@Component
public class FourthApplicationRunner implements ApplicationRunner, Ordered {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("执行顺序：【{}】", getOrder());
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
