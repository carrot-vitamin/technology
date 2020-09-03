package com.project.java.technology.senior.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinshaobo
 * @date 2019/4/17 10:27
 * @description
 */
@Slf4j
public class JwtUtils {

    /**
     * token失效时间 15分钟
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "0f4acf4365e3464a9c893f4b46eab6e1";

    private static final String APP_KEY = "appKey";

    public static String sign(String appKey) {
        String sign = null;
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //附带appKey生成签名
            sign = JWT.create()
                    .withHeader(header)
                    .withClaim(APP_KEY, appKey)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return sign;
    }

    public static boolean verify(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT != null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
