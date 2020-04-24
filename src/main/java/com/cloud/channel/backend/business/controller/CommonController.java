package com.cloud.channel.backend.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.CommonEnum;
import com.cloud.channel.backend.business.objects.pojo.User;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;
import com.cloud.channel.backend.core.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.channel.backend.business.service.BaseService;

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
        return ResponseResult.success(444);
    }

    @GetMapping("/test/{value}")
    public boolean testException(@PathVariable int value) {
        System.out.println("开始新增...");
        //如果姓名为空就手动抛出一个自定义的异常！
        baseService.ssss(value);
        if(true){
            throw  new BizException(CommonEnum.SERVER_BUSY);
        }
        return true;
    }

    @GetMapping("/test2/{value}")
    public ResponseResult test2(@PathVariable int value) {
        User user = AppContext.currentUser();
        return ResponseResult.success(JSONObject.toJSON(user));
    }

}
