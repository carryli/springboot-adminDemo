package com.cloud.channel.backend.business.entity;

import com.lamfire.jmongo.annotations.Entity;
import com.lamfire.jmongo.annotations.Id;
import com.lamfire.jmongo.annotations.Indexed;

import lombok.Data;

/**
 * 通信密玥
 */
@Entity
@Data
public class SecretKey {
    /**
     * 服务器id 用户中心服务器样式100X,金币服务中心服务器样式200X，服务管理中心服务器样式300X， 大厅服务器样式400X,游戏服务器样式 500X，后台服务器样式 600X，数据分析中心 700X
     */
    @Id
    private int serverId;
    /**
     * 服务器类型 1-用户中心 2-金币服务中心 3-服务管理中心 4-大厅 5-游戏服务中心 6-后台 7-数据分析中心
     */
    @Indexed
    private int serverType;
    /**
     * 16位 AESKey 后台与对应服务器数据交互秘钥。
     */
    private String secretKey;

}