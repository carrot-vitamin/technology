package com.project.java.technology.senior.service.impl;

import com.project.java.technology.senior.mapper.SecretTokenMapper;
import com.project.java.technology.senior.model.SecretToken;
import com.project.java.technology.senior.service.ISecretTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yinshaobo
 * @date 2019/4/17 15:11
 * @description
 */
@Service
public class SecretTokenServiceImpl implements ISecretTokenService {

    @Autowired
    private SecretTokenMapper secretTokenMapper;

    @Override
    public SecretToken getByAppKey(String appKey) {
        return this.secretTokenMapper.getByAppKey(appKey);
    }
}
