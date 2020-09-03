package com.project.java.technology.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.Date;

@Setter
@Getter
@ToString
public class User {

    private Long id;
    private String name;
    private Date birth;
    private File note;
    private File photo;
}
