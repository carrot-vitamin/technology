package com.project.java.technology.senior.batch;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author za-yinshaobo
 * @date 2020/10/17 16:37
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -8746443994791060631L;

    private String id;

    @Size(min = 2, max = 8)
    private String name;

    private int age;

    private String gender;
}
