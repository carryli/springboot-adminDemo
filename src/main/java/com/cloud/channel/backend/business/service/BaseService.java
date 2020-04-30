package com.cloud.channel.backend.business.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Bruce
 * @classname BaseService
 * @description 基础service
 * @date 2020/4/30 0030 11:02
 */
public interface BaseService {
    /**
     * 发送内部服务请求
     * 
     * @param apiUrl
     * @param cmd
     * @param params
     * @return
     */
    JSONObject sendRequest(String apiUrl, int cmd, JSONObject params);
}
