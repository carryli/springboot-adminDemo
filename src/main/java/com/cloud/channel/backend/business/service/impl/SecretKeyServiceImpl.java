package com.cloud.channel.backend.business.service.impl;

import java.util.HashMap;
import java.util.List;

import com.cloud.channel.backend.business.service.SecretKeyService;
import org.apache.commons.lang3.StringUtils;
import com.cloud.channel.backend.business.constant.ResponseCodeEnum;
import com.cloud.channel.backend.business.dao.BaseDao;
import com.cloud.channel.backend.business.entity.SecretKey;
import com.cloud.channel.backend.util.AESUtils;
import com.cloud.channel.backend.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
public class SecretKeyServiceImpl implements SecretKeyService {

    private final BaseDao<SecretKey> secretKeyDao = BaseDao.getInstance(SecretKey.class);
    /**
     * 服务id
     */
    @Value("${channel.serverId}")
    private String serverId;

    /**
     * aes秘钥
     */
    @Value("${channel.aesKey}")
    private String aesKey;

    /**
     * 服务类型
     */
    @Value("${channel.serverType}")
    private String serverType;

    /**
     * 用户中心Api地址
     */
    @Value("${channel.userApiUrl}")
    private String userApiUrl;

    /**
     * 金币中心Api地址
     */
    @Value("${channel.goldApiUrl}")
    private String goldApiUrl;

    /**
     * 服务管理中心Api地址
     */
    @Value("${channel.serverApiUrl}")
    private String serverApiUrl;

    /**
     * 数据分析中心Api地址
     */
    @Value("${channel.dataApiUrl}")
    private String dataApiUrl;

    /**
     * 管理后台Api地址
     */
    @Value("${channel.adminApiUrl}")
    private String adminApiUrl;

    /**
     * 无限代Api地址
     */
    @Value("${channel.resellerUrl}")
    private String resellerUrl;

    /**
     * 渠道中心Api地址
     */
    @Value("${channel.channelApiUrl}")
    private String channelApiUrl;

    /**
     * 活动系统Api地址
     */
    @Value("${channel.activityApiUrl}")
    private String activityApiUrl;

    /**
     * 加密服地址
     */
    @Value("${channel.signUrl}")
    private String signUrl;

    /**
     * 加密服地址
     */
    @Value("${mongodb.zone}")
    private String zone;

    /**
     * 加密服地址
     */
    @Value("${mongodb.dbName}")
    private String dbName;

    @Override
    public void exchangeSecretKey(int cmd, JSONObject params, String signUrl, String apiUrl) {
        // 请求头
        HashMap<String, String> headers = new HashMap<>(16);
        String header = cmd + "|" + serverId + "|" + serverType;
        headers.put("header", header);
        try {
            String sign = SignUtils.sign(signUrl, params.toJSONString());
            String response = HttpClientUtil.doPost(apiUrl, sign, headers);
            if (StringUtils.isEmpty(response)) {
                log.error("秘钥交换, 响应的数据为空! apiUrl为: {}, cmd为: {}, 请求参数为: {}", apiUrl, cmd, params.toString());
                return;
            }
            // 响应数据解密
            String decrypt = AESUtils.decrypt(response, aesKey);
            // 转json对象
            JSONObject data = JSON.parseObject(decrypt);
            int code = data.getIntValue("code");
            if (code != ResponseCodeEnum.SUCCESS.getCode()) {
                log.error("秘钥交换发生异常! apiUrl为: {}, cmd为: {}, 请求参数为: {}", apiUrl, cmd, params.toString());
            }
        } catch (Exception e) {
            log.error("秘钥交换发生异常! apiUrl为: {}, cmd为: {}, 请求参数为: {}", apiUrl, cmd, params.toString(), e);
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
        paramJson.put("aesKey", aesKey);
//        // 用户中心
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.USER, paramJson, signUrl, userApiUrl));
//        // 金币中心
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.ORDER, paramJson, signUrl, goldApiUrl));
//        // 无限代
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.RESELLER, paramJson, signUrl, resellerUrl));
//        // 服务管理中心
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.SERVER, paramJson, signUrl, serverApiUrl));
//        // 数据中心
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.DATA, paramJson, signUrl, dataApiUrl));
//        // 管理后台
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.ADMIN, paramJson, signUrl, adminApiUrl));
//        // 渠道中心
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.CHANNEL, paramJson, signUrl, channelApiUrl));
//        // 活动系统
//        CompletableFuture.runAsync(() -> exchangeSecretKey(SecretCmd.ACTIVITY, paramJson, signUrl, activityApiUrl));
    }
}
