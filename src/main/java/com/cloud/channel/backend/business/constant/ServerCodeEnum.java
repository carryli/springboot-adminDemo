package com.cloud.channel.backend.business.constant;

/**
 * @author Bruce
 * @classname ServerCodeEnum
 * @description 协议码枚举
 * @date 2020/4/28 0028 21:09
 */
public interface ServerCodeEnum {
/*******************************************USER**************************************************/
    /**
     * 分页查询用户
     */
    int SELECT_USER_BY_PAGE = 1019;

/*******************************************CHANNEL**************************************************/
    /**
     * 分页查找渠道列表
     */
    int SELECT_CHANNEL_BY_PAGE = 10004;

    /**
     * 通过渠道信息id查找渠道列表
     */
    int SELECT_CHANNEL_BY_INFO_IDS = 10005;

    /**
     * 后台操作-创建渠道订单
     */
    int CREATE_CHANNEL_ORDER = 10006;

    /**
     * 后台操作-修改渠道订单状态
     */
    int UPDATE_CHANNEL_ORDER_STATUS = 10007;

    /**
     * 分页查找渠道订单
     */
    int SELECT_CHANNEL_ORDER_BY_PAGE = 10008;

    /**
     * 获取平台下渠道枚举
     */
    int SELECT_CHANNEL_ENUMS = 10009;
}
