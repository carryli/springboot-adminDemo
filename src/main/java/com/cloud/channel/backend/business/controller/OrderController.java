package com.cloud.channel.backend.business.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cloud.channel.backend.business.objects.param.PaymentInfoParam;
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

    /**
     * 查询支付信息
     * 
     * @return
     */
    @GetMapping("/selectMyPaymentInfo")
    public ResponseResult selectMyPaymentInfo() {

        return orderService.selectMyPaymentInfo();
    }

    /**
     * 新增/编辑支付信息
     * 
     * @param paymentInfoParam
     * @return
     */
    @PostMapping("/savePaymentInfo")
    public ResponseResult savePaymentInfo(@Valid @RequestBody PaymentInfoParam paymentInfoParam) {

        return orderService.savePaymentInfo(paymentInfoParam);
    }

    /**
     * 删除支付信息
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/deletePaymentInfo")
    public ResponseResult deletePaymentInfo(@RequestParam("id") String id) {

        return orderService.deletePaymentInfo(id);
    }
}
