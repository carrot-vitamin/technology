package com.project.java.technology.senior.annotation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ShaoBo Yin at 2020/12/10 18:26
 * 自定义注解，校验不为空字符串
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MyValidator.class})
public @interface MyNotBlank {

    // 默认错误消息
    String message() default "不允许为空串";

    // 分组
    Class[] groups() default {};

    // 负载
    Class[] payload() default {};
}
