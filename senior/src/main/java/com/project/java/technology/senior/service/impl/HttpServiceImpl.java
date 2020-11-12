package com.project.java.technology.senior.service.impl;

import com.alibaba.fastjson.JSON;
import com.project.java.technology.senior.service.IHttpService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpServiceImpl implements IHttpService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    @Override
    public String get(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 装载配置信息
        httpGet.setConfig(config);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        return this.getResult(response);
    }

    @Override
    public String get(String url, Map<String, Object> params) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return this.get(uriBuilder.build().toString());
    }

    @Override
    public String post(String url) throws Exception {
        return this.post(url, null);
    }

    /**
     * form表单提交
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public String post(String url, Map<String, Object> params) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        // 判断map是否为空，不为空则进行遍历，封装form表单对象
        if (params != null) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return this.getResult(response);
    }

    @Override
    public String postJson(String url, String json) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return this.getResult(response);
    }

    @Override
    public String postJson(String url, Map<String, Object> params) throws Exception {
        return this.postJson(url, JSON.toJSONString(params));
    }

    @Override
    public String postFile(String url, File file) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        //File file = new File("F:\\Ken\\1.PNG");
        //FileBody bin = new FileBody(file);

        //multipartEntityBuilder.addBinaryBody("file", file,ContentType.create("image/png"),"abc.pdf");
        //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
        //multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
        multipartEntityBuilder.addBinaryBody("file", file);
        //multipartEntityBuilder.addPart("comment", new StringBody("This is comment", ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addTextBody("comment", "this is comment");
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpResponse = httpClient.execute(httpPost);
        return this.getResult(httpResponse);
    }

    private boolean responseOK(HttpResponse response) {
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }

    private String getResult(HttpResponse response) throws Exception {
        if (responseOK(response)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        throw new Exception(String.valueOf(response.getStatusLine().getStatusCode()));
    }
}
