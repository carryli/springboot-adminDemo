package com.cloud.channel.backend.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Bruce
 * @classname ConfigBean
 * @description TODO
 * @date 2020/4/29 0029 16:03
 */
@ConfigurationProperties(prefix = "my.api.url")
@Data
public class ConfigBean {
    /**
     * 服务id
     */
    private String serverId;

    /**
     * aes秘钥
     */
    private String aesKey;

    /**
     * 服务类型
     */
    private String serverType;

    /**
     * 用户中心Api地址
     */
    private String userApiUrl;

    /**
     * 金币中心Api地址
     */
    private String goldApiUrl;

    /**
     * 服务管理中心Api地址
     */
    private String serverApiUrl;

    /**
     * 数据分析中心Api地址
     */
    private String dataApiUrl;

    /**
     * 管理后台Api地址
     */
    private String adminApiUrl;

    /**
     * 无限代Api地址
     */
    private String resellerUrl;

    /**
     * 渠道中心Api地址
     */
    private String channelApiUrl;

    /**
     * 活动系统Api地址
     */
    private String activityApiUrl;

    /**
     * 加密服地址
     */
    private String signUrl;

    /**
     * 加密服地址
     */
    private String zone;

    /**
     * 加密服地址
     */
    private String dbName;
}
