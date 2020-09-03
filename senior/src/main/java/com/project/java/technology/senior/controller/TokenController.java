package com.project.java.technology.senior.controller;

import com.project.java.technology.core.model.ResponseCodeEnum;
import com.project.java.technology.core.model.ResponseModel;
import com.project.java.technology.senior.model.SecretToken;
import com.project.java.technology.senior.service.ISecretTokenService;
import com.project.java.technology.senior.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yinshaobo
 * @date 2019/4/17 14:58
 * @description
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private ISecretTokenService secretTokenService;

    @GetMapping
    public ResponseModel getToken(String appKey) {
        SecretToken secretToken = this.secretTokenService.getByAppKey(appKey);
        if (secretToken != null) {
            return ResponseModel.success(JwtUtils.sign(appKey));
        } else {
            return ResponseModel.fail(ResponseCodeEnum.APP_KEY_NOT_EXIST);
        }
    }

    @GetMapping("/test")
    public ResponseModel tokenTest() {
        return ResponseModel.success(new Date());
    }
}
