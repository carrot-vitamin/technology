package com.project.java.technology.senior.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author za-yinshaobo at 2020/12/9 10:31
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验异常拦截
     *
     * @param e 参数校验异常
     * @return 响应数据
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public JSONObject validateHandle(ConstraintViolationException e) {
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
        log.error(msg.toString(), e);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "999999");
        jsonObject.put("message", msg.toString());
        return jsonObject;
    }

}
