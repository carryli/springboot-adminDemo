package com.cloud.channel.backend.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.entity.UserToken;
import com.cloud.channel.backend.business.objects.pojo.User;
import com.cloud.channel.backend.business.service.UserTokenService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.exception.ResponseCodeEnum;
import com.cloud.channel.backend.core.jwt.JwtUtils;

/**
 * @author Bruce
 * @classname MemberController
 * @description 会员控制器
 * @date 2020/4/22 0022 20:28
 */
@RestController
@RequestMapping(value = "member")
public class AccountController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestParam(value = "username", required = true) String username,
        @RequestParam(value = "password", required = true) String password) {

        // todo 调用admin查找用户api
        User user = new User();
        user.setId(100000L);
        user.setAccount("admin");
        user.setPlatId(1001);
        user.setChannelId(110000);
        user.setChannelName("大鲤鱼了");
        user.setChannelLevel(1);
        user.setPassWord("111111");
        if (!username.equals(user.getAccount()) || !password.equals(user.getPassWord())) {
            return ResponseResult.error(ResponseCodeEnum.USER_NOT_FOUND);
        }
        // Create Twt token
        String jwtToken = JwtUtils.geneJsonWebToken(tokenHead, user);
        // 存入userToken
        String token = jwtToken.substring(tokenHead.length());
        userTokenService.save(new UserToken(user.getId(), token));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", jwtToken);
        return ResponseResult.success(jsonObject);
    }
}
