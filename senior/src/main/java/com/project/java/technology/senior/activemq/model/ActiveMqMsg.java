package com.project.java.technology.senior.activemq.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yin
 * @desc ActiveMqMsg
 * @date 2018/12/1 23:02
 */
@Setter
@Getter
public class ActiveMqMsg implements Serializable {

    private static final long serialVersionUID = 7875683239825024510L;

    private Date createTime;

    private String createTimeStr;

    @Override
    public String toString() {
        return "ActiveMqMsg{" +
                "createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                '}';
    }
}
