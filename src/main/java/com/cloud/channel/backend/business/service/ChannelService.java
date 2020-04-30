package com.cloud.channel.backend.business.service;

import com.cloud.channel.backend.business.objects.param.ChannelQueryParam;
import com.cloud.channel.backend.core.ResponseResult;

/**
 * @author Bruce
 * @classname ChannelService
 * @description TODO
 * @date 2020/4/30 0030 10:34
 */
public interface ChannelService {
    /**
     * 分页查找渠道
     * @param channelQueryParam
     * @return
     */
    ResponseResult selectChannelByPage(ChannelQueryParam channelQueryParam);
}
