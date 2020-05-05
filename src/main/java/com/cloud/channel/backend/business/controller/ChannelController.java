package com.cloud.channel.backend.business.controller;

import com.cloud.channel.backend.business.objects.param.ChannelQueryParam;
import com.cloud.channel.backend.business.objects.param.UserQueryParam;
import com.cloud.channel.backend.business.service.ChannelService;
import com.cloud.channel.backend.core.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Bruce
 * @classname ChannelController
 * @description 渠道
 * @date 2020/4/29 0029 18:08
 */
@Slf4j
@RestController
@RequestMapping(value = "channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    /**
     * 分页查找渠道
     * @param channelQueryParam
     * @return
     */
    @PostMapping("/selectChannelByPage")
    public ResponseResult selectChannelByPage(@Valid @RequestBody ChannelQueryParam channelQueryParam) {
        return channelService.selectChannelByPage(channelQueryParam);
    }
}
