package com.cloud.channel.backend.business.objects.param;

import com.lamfire.jmongo.annotations.Indexed;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Bruce
 * @classname ChannelQueryParam
 * @description 渠道查询入参
 * @date 2020/4/29 0029 19:53
 */
@Data
public class ChannelQueryParam {

    /**
     * 平台id
     */
    private Integer platId;

    /**
     * 渠道ID 渠道ID规则：6位数ID，前2位数代表一级渠道，中间2位数代表二级渠道，后2位数代表3级渠道，在ID上提现渠道上下级关系，如：三级渠道为：122201，其上级渠道为：122200，顶级渠道为：120000
     */
    private Integer channelId;

    /**
     * 渠道名称
     */
    private String channelName;

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
