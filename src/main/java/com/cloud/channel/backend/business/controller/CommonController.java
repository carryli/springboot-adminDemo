package com.cloud.channel.backend.business.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.objects.pojo.User;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;
import com.cloud.channel.backend.core.exception.BizException;
import com.cloud.channel.backend.core.exception.ResponseCodeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by Bruce
 * @description 通用构造器
 * @date
 **/
@RestController
@Slf4j
@Validated
public class CommonController {

    @GetMapping("/test")
    public ResponseResult test() {
        return ResponseResult.success(444);
    }

    @GetMapping("/test/{value}")
    public boolean testException(@PathVariable int value) {
        System.out.println("开始新增...");
        // 如果姓名为空就手动抛出一个自定义的异常！
        if (true) {
            throw new BizException(ResponseCodeEnum.SERVER_BUSY);
        }
        return true;
    }

    @PostMapping("/testJson")
    public ResponseResult testJson(@RequestBody JSONObject jsonObject) {

        return ResponseResult.success(jsonObject);
    }

    @PostMapping("/testPost")
    public ResponseResult testPost(@RequestParam("a") String a, @RequestParam("b") String b) {

        return ResponseResult.success(a + "," + b);
    }

    @GetMapping("/test2/{value}")
    public ResponseResult test2(@PathVariable int value) {
        User user = AppContext.currentUser();
        return ResponseResult.success(JSONObject.toJSON(user));
    }

}
