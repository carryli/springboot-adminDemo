package com.cloud.channel.backend.core;

import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.CommonEnum;

/**
 * @author Bruce
 * @classname addd
 * @description TODO
 * @date 2020/4/24 0024 18:23
 */
public class ResponseResult {
    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getDescription();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResponseResult success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResponseResult success(Object data) {
        ResponseResult rb = new ResponseResult();
        rb.setCode(CommonEnum.SUCCESS.getCode());
        rb.setMessage(CommonEnum.SUCCESS.getDescription());
        rb.setData(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResponseResult error(BaseErrorInfoInterface errorInfo) {
        ResponseResult rb = new ResponseResult();
        rb.setCode(errorInfo.getCode());
        rb.setMessage(errorInfo.getDescription());
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResponseResult error(int code, String message) {
        ResponseResult rb = new ResponseResult();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResponseResult error(String message) {
        ResponseResult rb = new ResponseResult();
        rb.setCode(-1);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
