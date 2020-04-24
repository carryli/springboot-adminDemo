package com.cloud.channel.backend.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.channel.backend.business.service.UserTokenService;

/**
 * @author Bruce
 * @classname MemberController
 * @description 会员控制器
 * @date 2020/4/22 0022 20:28
 */
@RestController
@RequestMapping(value = "member")
public class MemberController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserTokenService userTokenService;

//    @PostMapping(value = "/login")
//    public ResponseResult login(@RequestParam(value = "username", required = true) String username,
//        @RequestParam(value = "password", required = true) String password) {
//
//        // todo 调用admin查找用户api
//        User user = new User();
//        user.setId(100000L);
//        user.setAccount("admin");
//        user.setPassWord("111111");
//        if (!username.equals(user.getAccount()) || !password.equals(user.getPassWord())) {
//            return ResponseResult.error(ResponseCodeEnum.USER_NOT_FOUND.getCode());
//        }
//        // Create Twt token
//        String jwtToken = JwtUtils.geneJsonWebToken(tokenHead, user);
//        // 存入userToken
//        String token = jwtToken.substring(tokenHead.length());
//        userTokenService.save(new UserToken(user.getId(), token));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("token", jwtToken);
//        return ResponseResult.success(jsonObject);
//    }
}
