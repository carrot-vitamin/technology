package com.project.java.technology.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yin
 * @desc ResponseCodeEnum
 * @date 2018/9/22 4:27
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCodeEnum {

    /**
     *
     */
    SUCCESS("000000", "请求成功"),

    FAILURE("999999", "请求失败"),

    UPLOAD_FILE_EMPTY("000100", "上传文件为空"),

    FILE_NOT_EXIST("000101", "未找到指定文件"),

    AUTH_FAILURE("000102", "token认证失败");

    private String code;

    private String message;
}
