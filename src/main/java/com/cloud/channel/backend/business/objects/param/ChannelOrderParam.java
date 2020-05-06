package com.cloud.channel.backend.business.objects.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Bruce
 * @classname ChannelOrderParam
 * @description TODO
 * @date 2020/5/6 0006 14:38
 */
@Data
public class ChannelOrderParam implements Serializable {
    private static final long serialVersionUID = -8450128541745697890L;

    /**
     * 渠道ID
     */
    private Integer channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 提款时间start (13位时间戳)
     */
    private Long createAtStart;

    /**
     * 提款时间end (13位时间戳)
     */
    private Long createAtEnd;

    /**
     * 订单状态 0:待审核 1:已完成 2:已拒绝
     */
    private Integer status;

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
}
