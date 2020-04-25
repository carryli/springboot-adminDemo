package com.cloud.channel.backend.core.exception;

/**
 * @author Bruce
 * @classname BaseErrorInfoInterface
 * @description 基准错误信息接口
 * @date 2020/4/24 0024 18:20
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     *
     * @return
     */
    int getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getDescription();
}
