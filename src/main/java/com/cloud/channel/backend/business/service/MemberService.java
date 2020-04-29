package com.cloud.channel.backend.business.service;

import com.cloud.channel.backend.business.objects.param.UserQueryParam;
import com.cloud.channel.backend.core.ResponseResult;

/**
 * @author Bruce
 * @classname MemberService
 * @description 会员服务
 * @date 2020/4/29 0029 11:49
 */
public interface MemberService {
    /**
     * 分页查找会员列表
     * @param userQueryParam
     * @return
     */
    ResponseResult selectUserByPage(UserQueryParam userQueryParam);
}
