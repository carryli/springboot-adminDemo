package com.cloud.channel.backend.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cloud.channel.backend.core.exception.ResponseCodeEnum;
import com.cloud.channel.backend.business.entity.UserToken;
import com.cloud.channel.backend.business.objects.pojo.User;
import com.cloud.channel.backend.business.service.UserTokenService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;
import com.cloud.channel.backend.core.jwt.JwtUtils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

/**
 * @author by Bruce
 * @description 过滤未认证请求，token校验
 * @date
 **/
@Component
@Slf4j
public class LocalRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserTokenService userTokenService;

    /**
     * 构建认证失败响应消息
     * 
     * @param response
     * @return
     */
    private String getResponseString(HttpServletResponse response) {
        // 构建responseResult
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        ResponseResult error = ResponseResult.error(ResponseCodeEnum.AUTH_FAILED);
        error.setData(null);
        return JSONObject.toJSONString(error, SerializerFeature.WriteMapNullValue);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        // 获取请求头部分的Authorization
        String authHeader = request.getHeader(this.header);
        String url = request.getRequestURI().substring(request.getContextPath().length());

        // 增加过滤请求 附:同时得在这里配置？ com.cloud.channel.backend.core.config.WebSecurityConfig.configure
        if ("/member/login".equals(url) || "/test".equals(url)) {
            chain.doFilter(request, response);
            return;
        }
        if (null == authHeader || !authHeader.startsWith(this.tokenHead)) {
            log.info("authHeader={}", authHeader);
            String s = getResponseString(response);
            response.getWriter().print(JSONObject.toJSON(s));
            return;
        }
        // 去除tokenHeader
        final String token = authHeader.substring(tokenHead.length());
        Claims claims = JwtUtils.checkToken(token);
        // 校验token是否存在
        UserToken userToken = userTokenService.selectByToken(token);
        if (userToken == null) {
            log.info("token={}", token);
            String s = getResponseString(response);
            response.getWriter().print(JSONObject.toJSON(s));
            return;
        }
        User user = JSONObject.parseObject(claims.get("user").toString(), User.class);
        if (user == null || user.getId() == null || !userToken.getUserId().equals(user.getId())) {
            log.info("token={}", token);
            String s = getResponseString(response);
            response.getWriter().print(JSONObject.toJSON(s));
            return;
        }

        // 设置当前登录用户
        try (AppContext appContext = new AppContext(user)) {
            chain.doFilter(request, response);
        }
    }

}
