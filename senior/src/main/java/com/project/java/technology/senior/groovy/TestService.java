package com.project.java.technology.senior.groovy;

import org.springframework.stereotype.Service;

/**
 * @author za-yinshaobo at 2020/12/11 17:38
 */
@Service
public class TestService {

    public String testQuery(long id){
        return "Test query success, id is " + id;
    }
}
