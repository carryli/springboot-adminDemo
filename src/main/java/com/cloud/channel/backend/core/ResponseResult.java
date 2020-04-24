package com.cloud.channel.backend.core;

import java.io.Serializable;

import com.cloud.channel.backend.business.constant.ResponseCodeEnum;
import org.apache.commons.lang3.StringUtils;

import lombok.Data;

/**
 * @author by Bruce
 * @description 返回结果集
 * @date
 **/
@Data
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = 6088770867376061002L;

    public static final int ERROR = -1;
    public static final String ERROR_MESSAGE = "error!";
    public static final int SUCCESS = 1;
    public static final String SUCCESS_MESSAGE = "success!";
    /**
     * 返回状态
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    private ResponseResult(int status, String message, Object data) {
        super();
        this.code = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功-无参-
     * 
     * @return
     */
    public static ResponseResult success() {
        return new ResponseResult(SUCCESS, SUCCESS_MESSAGE, null);
    }

    /**
     * 成功-data
     * 
     * @param data
     * @return
     */
    public static ResponseResult success(Object data) {
        return new ResponseResult(SUCCESS, SUCCESS_MESSAGE, data);
    }

    /**
     * 失败-message
     * 
     * @param message
     * @return
     */
    public static ResponseResult error(String message) {
        return new ResponseResult(ERROR, message, null);
    }

    /**
     * 失败-code 根据code拼接错误信息
     *
     * @param code
     * @return
     */
    public static ResponseResult error(int code) {
        String description = ResponseCodeEnum.getByCode(code).getDescription();
        if (StringUtils.isEmpty(description)) {
            description = "网络异常!";
        }
        return new ResponseResult(code, description, null);
    }

    /**
     * 失败-code,message
     * 
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult error(int code, String message) {
        return new ResponseResult(code, message, null);
    }
}
