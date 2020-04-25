package com.cloud.channel.backend.core.exception;

/**
 * @author Bruce
 * @classname CodeEnum
 * @description 状态码枚举
 * @date 2020/4/24 0024 18:21
 */
public enum ResponseCodeEnum implements BaseErrorInfoInterface {
    /**
     * 成功!
     */
    SUCCESS(200, "成功!"),
    /**
     * 用户不存在!
     */
    USER_NOT_FOUND(400, "请求的数据格式不符!"),
    /**
     * 请求方式不支持!
     */
    REQUEST_METHOD_NOT_SUPPORT(401, "请求方式不支持!"),
    /**
     * 请求参数错误!
     */
    REQUEST_PARAMS_ERROR(402, "请求参数错误!"),
    /**
     * token认证失败!
     */
    AUTH_FAILED(403, "token认证失败!"),
    /**
     * 未找到该资源!
     */
    NOT_FOUND(404, "未找到该资源!"),
    /**
     * 服务器内部错误!
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    /**
     * 服务器正忙，请稍后再试!
     */
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),

    ;

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误描述
     */
    private final String description;

    ResponseCodeEnum(int resultCode, String resultMsg) {
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
