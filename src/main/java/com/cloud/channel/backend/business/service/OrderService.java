package com.cloud.channel.backend.business.service;

import com.cloud.channel.backend.business.objects.param.PaymentInfoParam;
import com.cloud.channel.backend.core.ResponseResult;

/**
 * @author Bruce
 * @classname OrderService
 * @description TODO
 * @date 2020/4/30 0030 18:06
 */
public interface OrderService {
    /**
     * 查询支付信息
     * 
     * @return
     */
    ResponseResult selectMyPaymentInfo();

    /**
     * 新增/编辑支付信息
     * 
     * @param paymentInfoParam
     * @return
     */
    ResponseResult savePaymentInfo(PaymentInfoParam paymentInfoParam);

    /**
     * 删除支付信息
     * 
     * @param paymentInfoId
     * @return
     */
    ResponseResult deletePaymentInfo(String paymentInfoId);
}
