package com.cloud.channel.backend.business.service.impl;

import org.springframework.stereotype.Service;

import com.cloud.channel.backend.business.dao.BaseDao;
import com.cloud.channel.backend.business.entity.UserToken;
import com.cloud.channel.backend.business.service.UserTokenService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname UserTokenServiceImpl
 * @description TODO
 * @date 2020/4/23 0023 20:23
 */

@Slf4j
@Service
public class UserTokenServiceImpl implements UserTokenService {

    private final BaseDao<UserToken> secretKeyDao = BaseDao.getInstance(UserToken.class);

    @Override
    public void save(UserToken userToken) {
        secretKeyDao.save(userToken);
    }

    @Override
    public UserToken selectByUserId(String userId) {
        return secretKeyDao.findOne("userId", userId);
    }

    @Override
    public UserToken selectByToken(String token) {
        return secretKeyDao.findOne("token", token);
    }

}
