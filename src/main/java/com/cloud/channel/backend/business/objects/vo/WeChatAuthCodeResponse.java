package com.cloud.channel.backend.business.objects.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author by Bruce
 * @description
 * @date
 **/
@Data
public class WeChatAuthCodeResponse implements Serializable {
    private static final long serialVersionUID = -4098790627496077841L;

    /**
     * 用户唯一标识
     */
    private String appid;
    /**
     * 用户唯一标识
     */
    private String session_key;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     */
    private String unionid;
    /**
     * 错误码
     */
    private String errcode;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * openid
     */
    private String openid;
    /**
     * 失效时间戳
     */
    private Long expiresIn;

}
