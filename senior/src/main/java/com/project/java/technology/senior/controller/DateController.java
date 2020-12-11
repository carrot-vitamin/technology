package com.project.java.technology.senior.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ShaoBo Yin at 2020/12/8 15:54
 */
@RestController
@RequestMapping("/date")
public class DateController {

    @Data
    public static class DateClass {

        /**
         * 定义输入格式
         * <p>@DateTimeFormat 用于form表单提交方式</P>
         * <p>@JsonFormat 用于body提交方式</P>
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date inputDate;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date outputDate;
    }

    @PostMapping("/test")
    public DateClass test(DateClass dateClass) {
        return dateClass;
    }
}
