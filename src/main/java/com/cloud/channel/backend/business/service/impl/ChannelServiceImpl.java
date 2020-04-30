package com.cloud.channel.backend.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.ServerCodeEnum;
import com.cloud.channel.backend.business.objects.param.ChannelQueryParam;
import com.cloud.channel.backend.business.service.ChannelService;
import com.cloud.channel.backend.business.service.SecretKeyService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;
import com.cloud.channel.backend.core.config.ConfigBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname ChannelServiceImpl
 * @description 渠道service实现
 * @date 2020/4/30 0030 10:54
 */
@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private SecretKeyService secretKeyService;
    @Autowired
    private ConfigBean configBean;

    @Override
    public ResponseResult selectChannelByPage(ChannelQueryParam channelQueryParam) {
        // 获取平台id
        Integer platId = AppContext.currentUser().getPlatId();
        Integer channelId = AppContext.currentUser().getChannelId();
        JSONObject params = (JSONObject)JSON.toJSON(channelQueryParam);
        params.put("platId", platId);
        // 查以自身id为上级id的渠道
        params.put("superiorChannelId", channelId);
        JSONObject resultJson =
            secretKeyService.sendRequest(configBean.getChannelApiUrl(), ServerCodeEnum.SELECT_CHANNEL_BY_PAGE, params);
        resultJson.put("channelList", resultJson.get("data"));
        resultJson.remove("data");
        return ResponseResult.success(resultJson);
    }
}
