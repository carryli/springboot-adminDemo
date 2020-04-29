package com.cloud.channel.backend.business.objects.param;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Bruce
 * @classname UserQueryParam
 * @description 查找用户入参
 * @date 2020/4/29 0029 10:41
 */
@Data
public class UserQueryParam {

    /**
     * 会员短号id
     */
    private Integer platUserId;

    /**
     * 会员昵称
     */
    private String nickname;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 渠道id
     */
    private Integer channelId;

    /**
     * 注册时间start (13位时间戳)
     */
    private Long registerAtStart;

    /**
     * 注册时间end (13位时间戳)
     */
    private Long registerAtEnd;

    /**
     * 首存时间start (13位时间戳)
     */
    private Long firstRechargeAtStart;

    /**
     * 首存时间end (13位时间戳)
     */
    private Long firstRechargeAtEnd;

    /**
     * 当前页码 最小值为1
     */
    @NotNull
    private Integer pageNumber;

    /**
     * 每页数量 最大值为200
     */
    @NotNull
    private Integer pageSize;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序 1为正序 -1为倒序
     */
    private Integer sortOrder;
}
