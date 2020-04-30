package com.cloud.channel.backend.core.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    USER_NOT_FOUND(400, "用户不存在!"),
    /**
     * 请求参数错误!
     */
    REQUEST_PARAMS_ERROR(401, "请求参数错误!"),
    /**
     * token已过期!
     */
    EXPIRED_TOKEN(402, "token已过期!"),
    /**
     * token认证失败!
     */
    AUTH_FAILED(403, "token认证失败!"),
    /**
     * 未找到该资源!
     */
    NOT_FOUND(404, "未找到该资源!"),
    /**
     * 请求方式不支持!
     */
    REQUEST_METHOD_NOT_SUPPORT(405, "请求方式不支持!"),
    /**
     * 服务器内部错误!
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    /**
     * 服务器正忙，请稍后再试!
     */
    SERVER_BUSY(503, "服务器正忙，请稍后再试!"),

    /****************************************** USER ****************************************************************/
    /**
     * 设备解析错误
     */
    DEVICE_VALID_ERROR(-1001, "设备解析错误"),
    /**
     * 设备已创建
     */
    DEVICE_REPEAT_ERROR(-1002, "设备已创建"),
    /**
     * 非法手机号码
     */
    ILLEGAL_MOBILE_ERROR(-1003, "非法手机号码"),
    /**
     * 手机号码已注册
     */
    MOBILE_REPEAT_RELATE_ERROR(-1004, "手机号码已注册"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST_ERROR(-1005, "用户不存在"),
    /**
     * token不存在
     */
    TOKEN_NOT_EXIST_ERROR(-1006, "token不存在"),
    /**
     * 参数验证失败
     */
    PARAMS_VALID_FAIL_ERROR(-1007, "参数验证失败"),
    /**
     * 用户已存在
     */
    USER_REPEAT_ERROR(-1008, "用户已存在"),
    /**
     * token已过期
     */
    TOKEN_EXPIRED_ERROR(-1009, "token已过期"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(-1010, "密码错误"),
    /**
     * 设备匹配失败
     */
    DEVICE_MATCH_ERROR(-1011, "设备匹配失败"),
    /**
     * 登录失败
     */
    LOGIN_FAIL_ERROR(-1012, "登录失败"),
    /**
     * 微信重复绑定错误
     */
    WX_REPEAT_RELATE_ERROR(-1013, "微信重复绑定错误"),
    /**
     * 非法昵称
     */
    INVALID_NICKNAME_ERROR(-1014, "非法昵称"),
    /**
     * 非法密码
     */
    INVALID_PASSWORD_ERROR(-1015, "非法密码"),
    /**
     * 邮件不存在
     */
    MAIL_NOT_EXIST_ERROR(-1016, "邮件不存在"),
    /**
     * 值不合法
     */
    INVALID_VALUE_ERROR(-1017, "值不合法"),
    /**
     * 支付信息不存在
     */
    PAYMENT_INFO_NOT_EXIST_ERROR(-1018, "支付信息不存在"),
    /**
     * 第三方账户不存在
     */
    THIRD_USER_NOT_EXIST_ERROR(-1019, "第三方账户不存在"),
    /**
     * 绑定支付信息超过上限
     */
    PAYMENT_OVER_BINDING_ERROR(-1020, "绑定支付信息超过上限"),
    /**
     * 重复进入游戏错误
     */
    REPEATED_PLAYING_STATUS_ERROR(-1021, "重复进入游戏错误"),
    /**
     * 账号被封号错误
     */
    USER_BE_KILLED_ERROR(-1022, "账号被封号错误"),
    /**
     * 该上级ID不存在
     */
    SUPERIOR_ID_NOT_EXIST_ERROR(-1023, "该上级ID不存在"),
    /**
     * 手机号未绑定
     */
    NOT_RELATE_MOBILE_ERROR(-1024, "手机号未绑定"),
    /**
     * 输入上级id错误
     */
    SUPERIOR_ID_ERROR(-1025, "输入上级id错误"),;

    /**
     * 错误码
     */
    private final int key;

    /**
     * 错误描述
     */
    private final String value;

    ResponseCodeEnum(int resultCode, String resultMsg) {
        this.key = resultCode;
        this.value = resultMsg;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static boolean containsKey(int key) {
        return Arrays.stream(ResponseCodeEnum.values()).map(ResponseCodeEnum::getKey).distinct()
            .anyMatch(s -> s == key);
    }

    public static boolean containsValue(String value) {
        for (ResponseCodeEnum userStatusEnum : ResponseCodeEnum.values()) {
            if (userStatusEnum.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getAllValue() {
        List<String> list = new ArrayList<>();
        for (ResponseCodeEnum responseCodeEnum : ResponseCodeEnum.values()) {
            list.add(responseCodeEnum.getValue());
        }
        return list;
    }

    public static String getValueByKey(int key) {
        for (ResponseCodeEnum userStatusEnum : ResponseCodeEnum.values()) {
            if (userStatusEnum.getKey() == key) {
                return userStatusEnum.getValue();
            }
        }
        return null;
    }

}
