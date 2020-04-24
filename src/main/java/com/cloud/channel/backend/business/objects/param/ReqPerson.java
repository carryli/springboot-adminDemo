package com.cloud.channel.backend.business.objects.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bruce
 * @classname ReqPerson
 * @description TODO
 * @date 2020/4/22 0022 20:34
 */
@Data
public class ReqPerson implements Serializable {
    private static final long serialVersionUID = 2543807061470126722L;

    private String  username;
    private String  password;
}
