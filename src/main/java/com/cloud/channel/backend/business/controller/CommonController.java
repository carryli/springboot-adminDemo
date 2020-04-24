package com.cloud.channel.backend.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.ResponseCodeEnum;
import com.cloud.channel.backend.business.objects.pojo.User;
import com.cloud.channel.backend.business.service.BaseService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;
import com.cloud.channel.backend.core.exception.BizException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by Bruce
 * @description 通用构造器
 * @date
 **/
@RestController
@Slf4j
public class CommonController {

    @Autowired
    private BaseService baseService;

    @GetMapping("/test")
    public ResponseResult test() {
        return ResponseResult.success("success");
    }

    @GetMapping("/test/{value}")
    public ResponseResult testException(@PathVariable int value) {
        int i = 0;
        try {
            if (value == 505) {
                throw new BizException(ResponseCodeEnum.AUTH_FAILED);
            }
            i = 9 / value;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new BizException(ResponseCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.success(i);
    }

    @GetMapping("/test2/{value}")
    public ResponseResult test2(@PathVariable int value) {
        baseService.ssss(value);
        User user = AppContext.currentUser();
        log.info("当前用户：{}", JSONObject.toJSON(user).toString());
        return ResponseResult.success();
    }

}
