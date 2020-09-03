package com.project.java.technology.senior.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author yin
 * @desc FileModel
 * @date 2018/9/22 4:04
 */
@Setter
@Getter
@ToString
public class FileModel {

    private Long id;

    private String origFileName;

    private String fileName;

    private String filePath;

    private String fileId;

    private String requestIP;

    private Date createTime;

    private Date updateTime;

}
