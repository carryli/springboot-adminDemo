package com.cloud.channel.backend.core.config;

import com.cloud.channel.backend.business.objects.pojo.User;

/**
 * @author by Bruce
 * @description 上下文应用，用于存储当前用户
 * @date
 **/
public class AppContext implements AutoCloseable {
    /**
     * 当前用户
     */
    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    public AppContext(User user) {
        CURRENT_USER.set(user);
    }

    @Override
    public void close() {
        CURRENT_USER.remove();
    }

    /**
     * 获取当前用户
     * 
     * @return
     */
    public static User currentUser() {
        return CURRENT_USER.get();
    }

}
