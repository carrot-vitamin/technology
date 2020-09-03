package com.project.java.technology.senior.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;

/**
 * @author za-yinshaobo
 * 2020/9/2 16:34
 * 自定义加密前缀
 */
public class MyEncryptablePropertyDetector implements EncryptablePropertyDetector {

    private static final String PREFIX = "java@";

    @Override
    public boolean isEncrypted(String property) {
        if (property != null) {
            return property.startsWith(PREFIX);
        }
        return false;
    }

    @Override
    public String unwrapEncryptedValue(String property) {
        return property.substring(PREFIX.length());
    }
}
