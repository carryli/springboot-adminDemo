package com.cloud.channel.backend.business.objects.param;

import java.io.Serializable;

import lombok.Data;

/**
 * @author by Bruce
 * @description
 * @date
 **/
@Data
public class WxUserInfo implements Serializable {
    private static final long serialVersionUID = -8156410109605748745L;

    private String nickName;

    private String avatarUrl;

    private Integer gender;

    private String country;

    private String province;

    private String city;

    private String language;
}
