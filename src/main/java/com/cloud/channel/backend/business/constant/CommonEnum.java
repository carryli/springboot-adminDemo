package com.cloud.channel.backend.business.constant;

import com.cloud.channel.backend.core.BaseErrorInfoInterface;

/**
 * @author Bruce
 * @classname ass
 * @description TODO
 * @date 2020/4/24 0024 18:21
 */
public enum CommonEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400, "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"),
    AUTH_FAILED(403, "token认证失败!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),
    ;

    /** 错误码 */
    private int code;

    /** 错误描述 */
    private String description;

    CommonEnum(int resultCode, String resultMsg) {
        this.code = resultCode;
        this.description = resultMsg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
