package com.project.java.technology.senior.service;


import com.project.java.technology.senior.model.SecretToken;

/**
 * @author yinshaobo
 * @date 2019/4/17 15:11
 * @description
 */
public interface ISecretTokenService {

    SecretToken getByAppKey(String appKey);
}
