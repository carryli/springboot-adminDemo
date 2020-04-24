package com.cloud.channel.backend.business.constant;

/**
 * @author by Bruce
 * @description 性别枚举
 * @date
 **/
public enum GenderEnum {
    /**
     * 未知
     */
    UNKNOWN(0, "未知"),
    /**
     * 女
     */
    FEMALE(1, "女"),
    /**
     * 男
     */
    MALE(2, "男");

    private int value;
    private String name;

    GenderEnum(int value, String name) {
        this.value = (byte)value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
