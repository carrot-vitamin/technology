package com.project.java.technology.senior.service;

import org.springframework.util.concurrent.ListenableFuture;

/**
 * @ClassName: IAsyncService
 * @Description:
 * @author: yinshaobo
 * @date: 2019/4/10 3:07 PM
 */
public interface IAsyncService {

    void asyncService();

    ListenableFuture<String> asyncService(String s);
}
