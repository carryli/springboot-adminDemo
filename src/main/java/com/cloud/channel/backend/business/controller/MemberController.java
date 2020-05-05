package com.cloud.channel.backend.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.channel.backend.business.objects.param.UserQueryParam;
import com.cloud.channel.backend.business.service.MemberService;
import com.cloud.channel.backend.core.ResponseResult;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

/**
 * @author Bruce
 * @classname MemberController
 * @description 会员
 * @date 2020/4/28 0028 20:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/selectUserByPage")
    public ResponseResult selectUserByPage(@Valid @RequestBody UserQueryParam userQueryParam) {
        return memberService.selectUserByPage(userQueryParam);
    }
}
