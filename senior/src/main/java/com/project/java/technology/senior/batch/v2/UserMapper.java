package com.project.java.technology.senior.batch.v2;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author ShaoBo Yin at 2020/11/30 16:45
 */
public class UserMapper implements FieldSetMapper<User> {

    @Override
    public User mapFieldSet(FieldSet fieldSet) throws BindException {
        User u = new User();
        u.setName(fieldSet.readString(0));
        u.setAge(fieldSet.readInt(1));
        return u;
    }
}
