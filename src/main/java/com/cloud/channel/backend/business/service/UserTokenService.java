package com.cloud.channel.backend.business.service;

import com.cloud.channel.backend.business.entity.UserToken;

/**
 * @author Bruce
 * @classname UserTokenService
 * @description TODO
 * @date 2020/4/23 0023 20:22
 */
public interface UserTokenService {
    /**
     * 保存
     * 
     * @param userToken
     */
    void save(UserToken userToken);

    /**
     * 通过userId查找
     * 
     * @param userId
     * @return
     */
    UserToken selectByUserId(String userId);

    /**
     * 通过token查找
     * 
     * @param token
     * @return
     */
    UserToken selectByToken(String token);
}
