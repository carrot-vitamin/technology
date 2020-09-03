package com.project.java.technology.senior.controller;

import com.project.java.technology.senior.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName: TestController
 * @Description:
 * @author: yinshaobo
 * @date: 2019/4/10 3:09 PM
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IAsyncService asyncService;

    @Autowired
    @Qualifier("asyncServiceExecutor")
    private TaskExecutor taskExecutor;

    @Resource(name = "jasyptStringEncryptor")
    private StringEncryptor stringEncryptor;

    @GetMapping("/async")
    public Date testAsync() {
        log.info("thread call... ...");
        this.asyncService.asyncService();
        return new Date();
    }

    @GetMapping("/async1")
    public Date testAsync1() {
        log.info("thread call... ...");
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return new Date();
    }

    @GetMapping("/encrypt")
    public String stringEncryptor(@RequestParam(value = "key") String key) {
        return stringEncryptor.encrypt(key);
    }
}
