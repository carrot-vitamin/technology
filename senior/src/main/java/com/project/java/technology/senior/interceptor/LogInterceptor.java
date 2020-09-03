package com.project.java.technology.senior.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogInterceptor {

    @Pointcut("execution(* com.project.java.technology.senior.controller..*(..))")
    public void validatorMethodPointcut() {}

    @Around("validatorMethodPointcut()")
    public Object aroundAdvisor(ProceedingJoinPoint proceedingJoinPoint) {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object result = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            StringBuilder argTypeBuilder = new StringBuilder();
            StringBuilder argValueBuilder = new StringBuilder();
            for (Object arg : args) {
                argTypeBuilder.append(arg.getClass().getName());
                argTypeBuilder.append(",");
                if (!disPrintParam(arg)) {
                    argValueBuilder.append(JSON.toJSONString(arg));
                } else {
                    log.info("无法打印参数：【{}】", arg.getClass().getName());
                }
            }
            log.info("拦截到请求：{}.{}({})", proceedingJoinPoint.getTarget().getClass().getName(), method.getName(), argTypeBuilder.toString());
            log.info("拦截到请求参数：{} ", argValueBuilder.toString());
        } catch (Exception e) {
            log.error("invoke method {} exception: {}", method.getName(), e);
        } finally {
            try {
                result = proceedingJoinPoint.proceed();
                log.info("拦截到返回数据：{}", JSON.toJSONString(result));
            } catch (Throwable throwable) {
                log.error("invoke method {} exception: {}", method.getName(), throwable);
            }
        }
        return result;
    }

    private boolean disPrintParam(Object o) {
        return o instanceof HttpServletRequest
                || o instanceof HttpServletResponse
                || o instanceof MultipartFile
                ;
    }

}
