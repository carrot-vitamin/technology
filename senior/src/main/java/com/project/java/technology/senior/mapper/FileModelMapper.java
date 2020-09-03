package com.project.java.technology.senior.mapper;


import com.project.java.technology.senior.model.FileModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yin
 * @desc FileModelMapper
 * @date 2018/9/22 4:41
 */
@Mapper
@Repository
public interface FileModelMapper {

    void insert(FileModel fileModel);

    FileModel findByFileId(String fileId);
}
