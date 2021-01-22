package com.project.java.technology.senior.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ShaoBo Yin
 * 2020/9/2 10:24
 */
@Slf4j
@Component
public class ThirdApplicationRunner implements ApplicationRunner, Ordered {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("执行顺序：【{}】", getOrder());
        log.info("redis start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        redisTemplate.opsForValue().set("k1", "v1");
        log.info("redis get k1 = {}", redisTemplate.opsForValue().get("k1"));
        redisTemplate.delete("k1");
        log.info("redis get k1 = {}", redisTemplate.opsForValue().get("k1"));
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
