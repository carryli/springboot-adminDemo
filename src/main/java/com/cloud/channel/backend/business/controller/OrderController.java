package com.cloud.channel.backend.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.channel.backend.business.service.OrderService;
import com.cloud.channel.backend.core.ResponseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bruce
 * @classname OrderController
 * @description 订单
 * @date 2020/4/28 0028 20:48
 */
@RestController
@Slf4j
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/selectMyPaymentInfo")
    public ResponseResult selectMyPaymentInfo() {

        return orderService.selectMyPaymentInfo();
    }
}
