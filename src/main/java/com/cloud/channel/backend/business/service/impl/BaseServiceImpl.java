package com.cloud.channel.backend.business.service.impl;

import com.cloud.channel.backend.business.service.BaseService;
import com.cloud.channel.backend.business.constant.ResponseCodeEnum;
import com.cloud.channel.backend.core.exception.BizException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname BaseServiceImpl
 * @description TODO
 * @date 2020/4/21 0021 10:39
 */
@Service
@Slf4j
public class BaseServiceImpl implements BaseService {

    @Override
    public void ssss(int value) {
        try {
            int i = 5 / value;
        } catch (Exception e) {
            throw new BizException(ResponseCodeEnum.PARAM_ERROR);
        }
    }
}
