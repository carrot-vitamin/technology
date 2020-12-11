package com.project.java.technology.senior.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author ShaoBo Yin
 * 2020/9/3 16:37
 */
public class Base64Utils {

    public static String file2Base64(String filePath) {
        byte[] data;
        try {
            InputStream in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static String encode(String string) {
        return Base64.encodeBase64URLSafeString(string.getBytes(Charset.defaultCharset()));
    }

    public static String decode(String string) {
        return new String(Base64.decodeBase64(string), Charset.defaultCharset());
    }
}
