package com.project.java.technology.senior.service.impl;

import com.project.java.technology.senior.mapper.FileModelMapper;
import com.project.java.technology.senior.model.FileModel;
import com.project.java.technology.senior.service.IFileModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yin
 * @desc FileModelServiceImpl
 * @date 2018/9/22 4:42
 */
@Service
public class FileModelServiceImpl implements IFileModelService {

    @Autowired
    private FileModelMapper modelMapper;

    @Override
    public void insert(FileModel fileModel) {
        modelMapper.insert(fileModel);
    }

    @Override
    public FileModel findByFileId(String fileId) {
        return modelMapper.findByFileId(fileId);
    }
}
