package com.cloud.channel.backend.business.constant;

/**
 * @author by Bruce
 * @description 错误码接口
 * @date
 **/
public interface ErrorCode {


    /**
     * 获取错误码
     *
     * @return
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return
     */
    String getDescription();
}