package com.project.java.technology.senior.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
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
    @ExceptionHandler(value = Exception.class)
    public JSONObject validateParamsHandle(Exception e) {
        log.error("全局异常捕捉... ...", e);
        JSONObject jsonObject;
        if (e instanceof ConstraintViolationException) {
            //针对直接对参数注解校验
            ConstraintViolationException exception = (ConstraintViolationException) e;
            jsonObject = this.validParamsResponse(exception);
        } else if (e instanceof BindException) {
            //针对入参为DTO对象的校验
            BindException exception = (BindException) e;
            jsonObject = this.validParamsResponse(exception);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            jsonObject = this.validParamsResponse(exception);
        } else {
            jsonObject = new JSONObject();
            jsonObject.put("code", "999999");
            jsonObject.put("message", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 针对直接对参数注解校验
     *
     * @return response
     */
    private JSONObject validParamsResponse(ConstraintViolationException exception) {
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = pathImpl.getLeafNode().getName();
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "999999");
        jsonObject.put("message", msg.toString());
        return jsonObject;
    }

    /**
     * 针对入参为DTO对象的校验
     *
     * @param exception MethodArgumentNotValidException
     * @return response
     */
    private JSONObject validParamsResponse(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return buildValidErrorResponse(bindingResult, exception);
    }

    private JSONObject validParamsResponse(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return buildValidErrorResponse(bindingResult, exception);
    }

    private JSONObject buildValidErrorResponse(BindingResult bindingResult, Exception exception) {
        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder builder = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();
                builder.append(field).append(":").append(defaultMessage).append("; ");
            }
            jsonObject.put("code", "999999");
            jsonObject.put("message", builder.toString());
        } else {
            jsonObject.put("code", "999999");
            jsonObject.put("message", exception.getMessage());
        }
        return jsonObject;
    }

}
