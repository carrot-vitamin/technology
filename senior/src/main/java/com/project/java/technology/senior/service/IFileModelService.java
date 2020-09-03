package com.project.java.technology.senior.service;


import com.project.java.technology.senior.model.FileModel;

/**
 * @author yin
 * @desc IFileModelService
 * @date 2018/9/22 4:39
 */
public interface IFileModelService {

    void insert(FileModel fileModel);

    FileModel findByFileId(String fileId);
}
