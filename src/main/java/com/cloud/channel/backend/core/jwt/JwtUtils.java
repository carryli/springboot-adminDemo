package com.cloud.channel.backend.core.jwt;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname Jwt工具
 * @description TODO
 * @date 2020/4/22 0022 21:07
 */
@Slf4j
public class JwtUtils {

    public static final String SUBJECT = "Authorization";
    /**
     * 秘钥
     */
    public static final String APP_SECRET = "Bearer";

    /**
     * 生成jwt
     *
     * @param secret
     *            加密串
     * @param object
     *            用户id
     * @return
     */
    public static String geneJsonWebToken(String secret, Object object) {
        String token = Jwts.builder().setSubject(SUBJECT).claim("user", JSONObject.toJSON(object).toString()).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();

        return secret + token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Claims checkToken(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
            return claims;

        } catch (Exception e) {
            log.error("校验token异常", e);
        }
        return null;
    }
}