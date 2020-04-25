package com.cloud.channel.backend.core.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname Ass
 * @description 日志记录切面,打印API相关信息
 * @date 2020/4/23 0023 18:13
 */
@Aspect
@Configuration
@Slf4j
public class LogRecordAspect {

    @Pointcut("execution(* com.cloud.channel.backend.business.controller..*.*(..))")
    public void executionService() {}

    @Around("executionService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = pjp.getArgs();
        String params = "";
        // 获取请求参数集合并进行遍历拼接
        if (args.length > 0) {
            if (HttpMethod.POST.name().equals(method)) {
                params = JSONObject.toJSONString(args);
            } else if (HttpMethod.GET.name().equals(method)) {
                params = queryString;
            }
        }
        long l1 = System.currentTimeMillis();
        log.info("请求开始===路径:{},请求方式:{},参数:{}", url, method, params);
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        long l2 = System.currentTimeMillis();
        log.info("请求结束===耗时:{}ms,返回值:{}", (l2 - l1), JSONObject.toJSON(result));
        return result;
    }

}
