package com.project.java.technology.senior.mapper;

import com.project.java.technology.senior.model.SecretToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yinshaobo
 * @date 2019/4/17 14:59
 * @description
 */
@Mapper
@Repository
public interface SecretTokenMapper {

    @Select({"select id,app_key as appKey,app_name as appName,created_time as createdTime from secret_token where app_key=#{appKey}"})
    SecretToken getByAppKey(String appKey);
}
