package com.cloud.channel.backend.business.entity;

import java.io.Serializable;
import java.util.Date;

import com.lamfire.jmongo.annotations.Entity;
import com.lamfire.jmongo.annotations.Id;
import com.lamfire.jmongo.annotations.IndexOptions;
import com.lamfire.jmongo.annotations.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bruce
 * @classname UserToken
 * @description 用户凭证
 * @date 2020/4/23 0023 19:52
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken implements Serializable {
    private static final long serialVersionUID = -5013990258496698601L;

    public UserToken(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Id
    private Long userId;

    /** jwt token */
    private String token;

    /** 过期时间 暂定一天过期 */
    @Indexed(options = @IndexOptions(expireAfterSeconds = 3600 * 24))
    private Date createAt = new Date();
}
