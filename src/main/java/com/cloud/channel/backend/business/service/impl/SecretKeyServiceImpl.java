package com.cloud.channel.backend.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.SecretCmd;
import com.cloud.channel.backend.business.dao.BaseDao;
import com.cloud.channel.backend.business.entity.SecretKey;
import com.cloud.channel.backend.business.service.SecretKeyService;
import com.cloud.channel.backend.core.config.ConfigBean;
import com.cloud.channel.backend.core.exception.BizException;
import com.cloud.channel.backend.core.exception.ResponseCodeEnum;
import com.cloud.channel.backend.util.AESUtils;
import com.cloud.channel.backend.util.CommonUtil;
import com.cloud.channel.backend.util.HttpClientUtil;
import com.lamfire.jmongo.query.QueryResults;
import com.safe.caverify.SignUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Classname SecretKeyServiceImpl
 * @Description 秘钥服务实现
 * @Date 2020/4/21 0021 10:38
 * @author Bruce
 */
@Service
@Slf4j
public class SecretKeyServiceImpl extends BaseServiceImpl implements SecretKeyService {

    private final BaseDao<SecretKey> secretKeyDao = BaseDao.getInstance(SecretKey.class);


    @Override
    public void exchangeSecretKey(int cmd, JSONObject params, String signUrl, String apiUrl) {
        // 请求头
        HashMap<String, String> headers = new HashMap<>(2);
        String header = cmd + "|" + configBean.getServerId() + "|" + configBean.getServerType();
        headers.put("header", header);
        String sign = null;
        try {
            sign = SignUtils.sign(signUrl, CommonUtil.toJsonString(params));
        } catch (Exception e) {
            log.error("加密错误", e);
        }
        String response = HttpClientUtil.doPost(apiUrl, sign, headers);
        if (StringUtils.isEmpty(response)) {
            log.error("发送请求错误:apiUrl={},cmd={},params={}", apiUrl, cmd, CommonUtil.toJsonString(params));
            return;
        }
        // 响应数据解密
        String decrypt = AESUtils.decrypt(response, configBean.getAesKey());
        // 转json对象
        JSONObject data = JSON.parseObject(decrypt);
        int code = data.getIntValue("code");
        if (code != ResponseCodeEnum.SUCCESS.getKey()) {
            log.error("秘钥交换发生异常! apiUrl为: {}, cmd为: {}, 请求参数为: {}", apiUrl, cmd, CommonUtil.toJsonString(params));
        }

    }

    @Override
    public List<SecretKey> getAllAesKey() {
        QueryResults<SecretKey> secretKeys = secretKeyDao.find();
        return secretKeys.asList();
    }

    @Override
    public void exchangeAll() {
        JSONObject paramJson = new JSONObject();
        paramJson.put("aesKey", configBean.getAesKey());
        // // 用户中心
        // CompletableFuture.runAsync(
        // () -> exchangeSecretKey(SecretCmd.USER, paramJson, configBean.getSignUrl(), configBean.getUserApiUrl()));
        // // 金币中心
        // CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.ORDER, paramJson, signUrl, goldApiUrl));
        // // 无限代
        // CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.RESELLER, paramJson, signUrl, resellerUrl));
        // // 服务管理中心
        // CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.SERVER, paramJson, signUrl, serverApiUrl));
        // // 数据中心
        // CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.DATA, paramJson, signUrl, dataApiUrl));
        // // 管理后台
        // CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.ADMIN, paramJson, signUrl, adminApiUrl));
        // 渠道中心
        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.CHANNEL, paramJson, configBean.getSignUrl(),
            configBean.getChannelApiUrl()));
        // // 活动系统
        // CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.ACTIVITY, paramJson, signUrl, activityApiUrl));
    }
}
