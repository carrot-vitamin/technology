package com.project.java.technology.senior.service;

import java.io.File;
import java.util.Map;

public interface IHttpService {

    String get(String url) throws Exception;

    String get(String url, Map<String, Object> params) throws Exception;

    String post(String url) throws Exception;

    String post(String url, Map<String, Object> params) throws Exception;

    String postJson(String url, String json) throws Exception;

    String postJson(String url, Map<String, Object> params) throws Exception;

    String postFile(String url, File file) throws Exception;
}
