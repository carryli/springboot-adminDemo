package com.cloud.channel.backend.business.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.ServerCodeEnum;
import com.cloud.channel.backend.business.objects.param.UserQueryParam;
import com.cloud.channel.backend.business.service.MemberService;
import com.cloud.channel.backend.business.service.SecretKeyService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;
import com.cloud.channel.backend.core.config.ConfigBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname MemberServiceImpl
 * @description 会员服务实现
 * @date 2020/4/29 0029 11:50
 */
@Slf4j
@Service
public class MemberServiceImpl extends BaseServiceImpl implements MemberService {


    @Override
    public ResponseResult selectUserByPage(UserQueryParam userQueryParam) {
        // 获取平台id
        Integer platId = AppContext.currentUser().getPlatId();
        JSONObject params = (JSONObject)JSON.toJSON(userQueryParam);
        params.put("platIds", Collections.singletonList(platId));
        JSONObject resultJson =
            sendRequest(configBean.getUserApiUrl(), ServerCodeEnum.SELECT_USER_BY_PAGE, params);
        resultJson.put("memberList", resultJson.get("data"));
        resultJson.remove("data");
        return ResponseResult.success(resultJson);
    }
}
