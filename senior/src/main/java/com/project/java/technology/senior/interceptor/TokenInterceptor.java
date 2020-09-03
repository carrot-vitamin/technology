package com.project.java.technology.senior.interceptor;

import com.alibaba.fastjson.JSON;
import com.project.java.technology.core.model.ResponseCodeEnum;
import com.project.java.technology.core.model.ResponseModel;
import com.project.java.technology.senior.util.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yinshaobo
 * @date 2019/4/17 15:21
 * @description
 */
public class TokenInterceptor implements HandlerInterceptor {

    private static final String TOKEN_KEY = "accessToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN_KEY);
        if (!JwtUtils.verify(token)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResponseModel.fail(ResponseCodeEnum.AUTH_FAILURE)));
            return false;
        }
        return true;
    }
}
