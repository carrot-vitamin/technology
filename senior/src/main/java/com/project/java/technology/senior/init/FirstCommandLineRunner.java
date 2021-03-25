package com.project.java.technology.senior.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author yinshaobo at 2021/1/22 18:32
 */
@Slf4j
@Component
public class FirstCommandLineRunner implements CommandLineRunner, Ordered {

    @Override
    public void run(String... args) throws Exception {
        log.info("执行顺序：【{}】", getOrder());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
