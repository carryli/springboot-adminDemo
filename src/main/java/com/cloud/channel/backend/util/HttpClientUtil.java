package com.cloud.channel.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.alibaba.fastjson.JSONObject;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2020 史阀龙, Inc. All rights reserved.
 * <p>
 * Company: 史阀龙科技有限公司
 * <p>
 *
 * @author laughing
 * @since 2020/4/7 22:38
 */
public class HttpClientUtil {

    /**
     * 发送POST请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param headers
     *            请求头
     * @return 响应
     * @throws Exception
     */
    public static String doPost(String url, String params, Map<String, String> headers) {
        try {
            // 请求地址
            PostMethod postMethod = new PostMethod(url);
            // 请求头
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                postMethod.setRequestHeader(key, value);
            }
            // 请求体
            StringRequestEntity entity = new StringRequestEntity(params, "application/json", "UTF-8");
            postMethod.setRequestEntity(entity);
            // 添加超时时间
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(15000);
            managerParams.setSoTimeout(20000);
            // 执行请求
            httpClient.executeMethod(postMethod);
            // 响应
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 发送GET请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @param headers
     *            请求头
     * @return 响应
     * @throws Exception
     */
    private static String doGet(String url, JSONObject params, Map<String, String> headers) throws Exception {
        // 组装带参的请求地址
        StringBuilder paramsBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            paramsBuilder.append(key).append("=").append(value).append("&");
        }
        String paramsString = paramsBuilder.toString();
        paramsString = paramsString.substring(0, paramsString.length() - 1);
        String requestUrl = url + "?" + paramsString;
        // GET请求
        GetMethod getMethod = new GetMethod(requestUrl);
        // 请求头
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            getMethod.setRequestHeader(key, value);
        }
        // 添加超时时间
        HttpClient httpClient = new HttpClient();
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
        managerParams.setConnectionTimeout(15000);
        managerParams.setSoTimeout(20000);
        // 执行请求
        httpClient.executeMethod(getMethod);
        // 响应
        InputStream inputStream = getMethod.getResponseBodyAsStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            builder.append(str);
        }
        return builder.toString();
    }

}
