package com.cloud.channel.backend.business.objects.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author by Bruce
 * @description
 * @date
 **/
@Data
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 2627250771845627675L;

    private String code;
}
