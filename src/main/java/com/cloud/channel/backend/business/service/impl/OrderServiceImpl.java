package com.cloud.channel.backend.business.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.channel.backend.business.constant.ServerCodeEnum;
import com.cloud.channel.backend.business.objects.param.PaymentInfoParam;
import com.cloud.channel.backend.business.objects.pojo.User;
import com.cloud.channel.backend.business.service.OrderService;
import com.cloud.channel.backend.core.ResponseResult;
import com.cloud.channel.backend.core.config.AppContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname OrderServiceImpl
 * @description TODO
 * @date 2020/4/30 0030 18:06
 */
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Override
    public ResponseResult selectMyPaymentInfo() {
        // 获取当前用户
        User user = AppContext.currentUser();
        JSONObject params = new JSONObject();
        params.put("platId", user.getPlatId());
        params.put("channelId", user.getChannelId());
        JSONObject resultJson = sendRequest(configBean.getChannelApiUrl(), ServerCodeEnum.SELECT_PAYMENT_INFO, params);
        resultJson.put("paymentInfoList", resultJson.get("data"));
        resultJson.remove("data");

        return ResponseResult.success(resultJson);
    }

    @Override
    public ResponseResult savePaymentInfo(PaymentInfoParam paymentInfoParam) {
        // 获取当前用户
        User user = AppContext.currentUser();
        JSONObject params = (JSONObject)JSON.toJSON(paymentInfoParam);
        params.put("platId", user.getPlatId());
        params.put("channelId", user.getChannelId());
        JSONObject resultJson = sendRequest(configBean.getChannelApiUrl(), ServerCodeEnum.SAVE_PAYMENT_INFO, params);

        return ResponseResult.success(resultJson);
    }

    @Override
    public ResponseResult deletePaymentInfo(String paymentInfoId) {
        // 获取当前用户
        User user = AppContext.currentUser();
        JSONObject params = new JSONObject();
        params.put("platId", user.getPlatId());
        params.put("channelId", user.getChannelId());
        params.put("id", paymentInfoId);
        JSONObject resultJson = sendRequest(configBean.getChannelApiUrl(), ServerCodeEnum.DELETE_PAYMENT_INFO, params);

        return ResponseResult.success(resultJson);
    }

    @Override
    public ResponseResult createChannelOrder(Long amount, String paymentInfo) {
        // 获取当前用户
        User user = AppContext.currentUser();
        JSONObject params = new JSONObject();
        params.put("platId", user.getPlatId());
        params.put("channelId", user.getChannelId());
        params.put("channelName", user.getChannelName());
        params.put("amount", amount);
        params.put("paymentInfo", paymentInfo);
        JSONObject resultJson = sendRequest(configBean.getChannelApiUrl(), ServerCodeEnum.CREATE_CHANNEL_ORDER, params);

        return ResponseResult.success(resultJson);
    }

    @Override
    public ResponseResult updateChannelOrderStatus(String status, String orderId) {
        // 获取当前用户
        User user = AppContext.currentUser();
        JSONObject params = new JSONObject();
        params.put("platId", user.getPlatId());
        params.put("channelId", user.getChannelId());
        params.put("status", status);
        params.put("orderId", orderId);
        JSONObject resultJson =
            sendRequest(configBean.getChannelApiUrl(), ServerCodeEnum.UPDATE_CHANNEL_ORDER_STATUS, params);

        return ResponseResult.success(resultJson);
    }

}
