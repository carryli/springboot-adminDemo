package com.cloud.channel.backend.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.service.BaseService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.ConfigBean;
import com.cloud.channel.backend.core.exception.BizException;
import com.cloud.channel.backend.core.exception.ResponseCodeEnum;
import com.cloud.channel.backend.util.AESUtils;
import com.cloud.channel.backend.util.CommonUtil;
import com.cloud.channel.backend.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Bruce
 * @classname BaseServiceImpl
 * @description TODO
 * @date 2020/4/30 0030 11:03
 */
@Slf4j
@Service
public abstract class BaseServiceImpl implements BaseService {


    @Autowired
    protected ConfigBean configBean;

    /**
     * 发送请求
     *
     * @param apiUrl
     *            请求地址
     * @param cmd
     *            命令
     * @param params
     *            请求参数
     * @return 请求结果
     */
    @Override
    public JSONObject sendRequest(String apiUrl, int cmd, JSONObject params) {
        // 请求参数AES加密
        String encrypt = AESUtils.encrypt(CommonUtil.toJsonString(params), configBean.getAesKey());
        // 请求头
        HashMap<String, String> headers = new HashMap<>(2);
        String header = cmd + "|" + configBean.getServerId() + "|" + configBean.getServerType();
        headers.put("header", header);
        // 发送请求
        String response = HttpClientUtil.doPost(apiUrl, encrypt, headers);
        if (StringUtils.isEmpty(response)) {
            log.error("请求目标服务器异常:apiUrl={},cmd={},params={}", apiUrl, cmd, CommonUtil.toJsonString(params));
            throw new BizException(ResponseCodeEnum.INTERNAL_SERVER_ERROR);
        }
        // 响应数据解密
        String decrypt = AESUtils.decrypt(response, configBean.getAesKey());
        // 转json对象
        JSONObject data = JSON.parseObject(decrypt);
        // 响应数据
        int code = data.getIntValue("code");
        if (code != ResponseCodeEnum.SUCCESS.getKey()) {
            log.error("请求非正常状态码:apiUrl={},cmd={},params={}", apiUrl, cmd, CommonUtil.toJsonString(params));
            throw new BizException(code);
        }
        // 移除状态码
        data.remove("code");
        return data;

    }

}
