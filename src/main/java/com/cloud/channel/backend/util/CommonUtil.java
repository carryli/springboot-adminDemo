package com.cloud.channel.backend.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Bruce
 * @classname CommonUtil
 * @description 通用工具类
 * @date 2020/4/23 0023 18:15
 */
public class CommonUtil {

    /**
     * 实体类转Map </br>
     * 过滤serialVersionUID </br>
     *
     * @param object
     * @return
     */
    public static Map<String, Object> objectToMap(Object object) {
        Map<String, Object> map = new HashMap<>(16);
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                // 过滤serialVersionUID
                if (StringUtils.equals("serialVersionUID", field.getName())) {
                    continue;
                }
                Object o = field.get(object);
                // // 过滤空值
                // if (o == null) {
                // continue;
                // }
                if (o instanceof String && StringUtils.isEmpty(String.valueOf(o))) {
                    continue;
                }
                map.put(field.getName(), o);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
