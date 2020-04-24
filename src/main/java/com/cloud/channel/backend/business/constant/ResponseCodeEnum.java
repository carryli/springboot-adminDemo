package com.cloud.channel.backend.business.constant;

/**
 * @author by Bruce
 * @description 业务错误码
 * @date
 **/
public enum ResponseCodeEnum implements ErrorCode {
    /**
     * 成功状态码
     */
    SUCCESS(200, "success!"),

    USER_NOT_FOUND(100003, "账号或密码不正确,今日第"),
    INVALID_TOKEN(402, "非法token"),
    AUTH_FAILED(403, "token认证失败"),
    HTTP_METHOD_NOT_SUPPORT(405, "不支持的请求类型"),

    /**
     * 未指明的异常
     */
    SYSTEM_ERROR(500, "网络异常，请稍后再试"),
    PARAM_ERROR(505, "参数错误"),


    ;

    /**
     * 错误码
     */
    private final int code;

    /**
     * 描述
     */
    private final String description;

    /**
     * @param code
     *            错误码
     * @param description
     *            描述
     */
    private ResponseCodeEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code
     *            编码。
     * @return 枚举。
     */
    public static ResponseCodeEnum getByCode(int code) {
        for (ResponseCodeEnum value : ResponseCodeEnum.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return SYSTEM_ERROR;
    }

    /**
     * 枚举是否包含此code
     *
     * @param code
     *            枚举code
     * @return 结果
     */
    public static Boolean contains(int code) {
        for (ResponseCodeEnum value : ResponseCodeEnum.values()) {
            if (code == value.getCode()) {
                return true;
            }
        }
        return false;
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