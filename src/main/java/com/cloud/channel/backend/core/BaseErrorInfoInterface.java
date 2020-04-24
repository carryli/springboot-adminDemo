package com.cloud.channel.backend.core;

/**
 * @author Bruce
 * @classname Afdf
 * @description TODO
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
