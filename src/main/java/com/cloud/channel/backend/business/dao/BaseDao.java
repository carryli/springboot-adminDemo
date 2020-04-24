package com.cloud.channel.backend.business.dao;

import com.lamfire.jmongo.dao.DAOSupport;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title BaseDao
 * @Description
 * @author Bruce
 */
public class BaseDao<T> extends DAOSupport<T, Object> {
    private static Map<Class, BaseDao> DAO_MAP = new ConcurrentHashMap<>();

    private BaseDao(Class<T> classes) {
        super("db0", "channel-backend", classes.getSimpleName(), classes);
    }

    public static <T> BaseDao<T> getInstance(Class<T> classess) {
        return DAO_MAP.computeIfAbsent(classess, v -> new BaseDao(classess));
    }
}
