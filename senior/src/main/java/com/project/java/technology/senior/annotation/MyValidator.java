package com.project.java.technology.senior.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ShaoBo Yin at 2020/12/10 18:28
 */
public class MyValidator implements ConstraintValidator<MyNotBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !"".equals(value.trim());
    }
}
