package com.project.java.technology.senior.service.impl;

import com.project.java.technology.senior.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @ClassName: AsyncServiceImpl
 * @Description:
 * @author: yinshaobo
 * @date: 2019/4/10 3:07 PM
 */
@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    @Async(value = "asyncServiceExecutor")
    @Override
    public void asyncService() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async(value = "asyncServiceExecutor")
    @Override
    public ListenableFuture<String> asyncService(String s) {
        return new AsyncResult<>(s);
    }
}
