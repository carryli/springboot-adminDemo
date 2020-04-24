package com.cloud.channel.backend.core.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
import com.cloud.channel.backend.business.constant.ResponseCodeEnum;
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
    public final static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (log.isInfoEnabled()) {
            log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
                //  以下是后期添加的 要是不想在数据库看到 0:0:0.....或者 127.0.0.1的 数字串可用下边方法 亲测
                if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
                    //根据网卡取本机配置的IP
                    InetAddress inet=null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ip= inet.getHostAddress();
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        // 获取请求头部分的Authorization
        String authHeader = request.getHeader(this.header);
        System.out.println(getIpAddress(request));
        // 如果请求路径为微信通知后台支付结果则不需要token（之后会在具体的controller中，对双方签名进行验证防钓鱼）
        String url = request.getRequestURI().substring(request.getContextPath().length());
        // 增加过滤请求 附:同时得在这里配置？ com.cloud.channel.backend.core.config.WebSecurityConfig.configure
        if ("/member/login".equals(url) || "/test".equals(url)) {
            chain.doFilter(request, response);
            return;
        }
        if (null == authHeader || !authHeader.startsWith(this.tokenHead)) {
            log.info("authHeader={}", authHeader);
            // 构建responseResult
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            ResponseResult error = ResponseResult.error(ResponseCodeEnum.AUTH_FAILED.getCode());
            error.setData(null);
            String s = JSONObject.toJSONString(error, SerializerFeature.WriteMapNullValue);
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
            // 构建responseResult
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            ResponseResult error = ResponseResult.error(ResponseCodeEnum.AUTH_FAILED.getCode());
            error.setData(null);
            String s = JSONObject.toJSONString(error, SerializerFeature.WriteMapNullValue);
            response.getWriter().print(JSONObject.toJSON(s));
            return;
        }
        User user = JSONObject.parseObject(claims.get("user").toString(), User.class);
        if (user == null || user.getId() == null || !userToken.getUserId().equals(user.getId())) {
            log.info("token={}", token);
            // 构建responseResult
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            ResponseResult error = ResponseResult.error(ResponseCodeEnum.AUTH_FAILED.getCode());
            error.setData(null);
            String s = JSONObject.toJSONString(error, SerializerFeature.WriteMapNullValue);
            response.getWriter().print(JSONObject.toJSON(s));
            return;
        }

        // 设置当前登录用户
        try (AppContext appContext = new AppContext(user)) {
            chain.doFilter(request, response);
        }
    }

}
