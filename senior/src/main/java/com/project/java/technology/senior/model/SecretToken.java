package com.project.java.technology.senior.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author yinshaobo
 * @date 2019/4/17 14:59
 * @description
 */
@Setter
@Getter
@ToString
public class SecretToken {

    private Integer id;

    private String appKey;

    private String appName;

    private Date createdTime;
}
