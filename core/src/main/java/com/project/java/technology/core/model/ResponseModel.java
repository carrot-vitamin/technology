package com.project.java.technology.core.model;

import lombok.Data;

/**
 * @author yin
 * @desc ResponseModel
 * @date 2018/9/22 4:25
 */
@Data
public class ResponseModel {

    private String code;

    private String message;

    private Object data;

    private ResponseModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseModel(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseModel success(Object data) {
        return new ResponseModel(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static ResponseModel fail(ResponseCodeEnum codeEnum) {
        return new ResponseModel(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static ResponseModel fail() {
        return new ResponseModel(ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMessage());
    }

}
