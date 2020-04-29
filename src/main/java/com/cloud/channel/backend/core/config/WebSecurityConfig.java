package com.cloud.channel.backend.core.config;

import com.cloud.channel.backend.core.filter.LocalRequestFilter;
import com.cloud.channel.backend.core.jwt.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author by Bruce
 * @description 可删?
 * @date
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(ConfigBean.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public LocalRequestFilter authenticationTokenFilterBean() {
        return new LocalRequestFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // 由于使用的是JWT，我们这里不需要csrf
            .csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            // 基于token，所以不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
            // 对于获取token的rest api要允许匿名访问
            .antMatchers("/test").permitAll().antMatchers("/member/login").permitAll();

        // 添加本地地三方session filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}