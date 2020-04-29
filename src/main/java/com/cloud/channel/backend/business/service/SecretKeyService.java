package com.cloud.channel.backend.business.service;

import java.util.List;

import com.cloud.channel.backend.business.entity.SecretKey;

import com.alibaba.fastjson.JSONObject;

/**
 * @description 通信密钥service
 * @author by Bruce
 * @date
 **/
public interface SecretKeyService {

    /**
     * 发送内部请求服务
     * @param apiUrl
     * @param cmd
     * @param params
     * @return
     */
    JSONObject sendRequest(String apiUrl, int cmd, JSONObject params);

    /**
     * 交换秘钥
     * 
     * @param cmd
     * @param params
     * @param signUrl
     * @param apiUrl
     */
    void exchangeSecretKey(int cmd, JSONObject params, String signUrl, String apiUrl);

    /**
     * 获取所有的aesKey
     *
     * @return
     */
    List<SecretKey> getAllAesKey();

    /**
     * 交换所有秘钥
     */
    void exchangeAll();
}
