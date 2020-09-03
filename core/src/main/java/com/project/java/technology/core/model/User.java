package com.project.java.technology.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -8405756299443560722L;
    private Long id;
    private String name;
    private Date birth;
    private File note;
    private File photo;
}
