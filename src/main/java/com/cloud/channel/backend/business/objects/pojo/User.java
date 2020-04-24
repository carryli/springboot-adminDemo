package com.cloud.channel.backend.business.objects.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author by Bruce
 * @description
 * @date
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -8790693008254506484L;

    /**
     * 管理员ID 1001-超级管理员(只负责管理员、权限、角色的分配权限)； 其它管理账号没有管理员、权限、角色的分配权限
     */
    private Long id;
    /**
     * 管理员账号
     */
    private String account;
    /**
     * 密码md5(密码)
     */
    private String passWord;
    /**
     * 1-平台顶级账号 2-普通账号
     */
    private Long accountType;
    /**
     * 管理员角色Id
     */
    private Long roleId;
    /**
     * 创建时间
     */
    private Long createAt;
    /**
     * 最后登陆时间
     */
    private Long lastLoginAt;
    /**
     * 错误次数 次数大于5次将会被锁定
     */
    private Integer failureNum;
    /**
     * 锁定标志0-正常 1 锁定 默认值0
     */
    private Integer lock;
    /**
     * 最后登录IP
     */
    private String lastLoginIp;
    /**
     * 平台ID
     */
    private Integer platId;
    /**
     * 渠道ID 0-全平台渠道
     */
    private Integer channelId;
    /**
     * 创建者-平台运营后台管理员ID
     */
    private Long superId;
    /**
     * 管理后台管理员ID
     */
    private Long adminId;
    /**
     * 可用金币
     */
    private Long goldCount;
    /**
     * 历史添加金币
     */
    private Long oldGoldCount;

}