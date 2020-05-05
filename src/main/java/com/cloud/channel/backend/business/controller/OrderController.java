package com.cloud.channel.backend.business.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
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

    /**
     * 创建渠道订单
     * 
     * @param amount
     * @param paymentInfo
     * @return
     */
    @PostMapping("/createChannelOrder")
    public ResponseResult createChannelOrder(
        @Min(value = 1, message = "amount不能小于1！") @RequestParam("amount") Long amount,
        @NotBlank @RequestParam("paymentInfo") String paymentInfo) {

        return orderService.createChannelOrder(amount, paymentInfo);
    }

    /**
     * 修改渠道订单状态
     * 
     * @param status
     * @param orderId
     * @return
     */
    @PostMapping("/updateChannelOrderStatus")
    public ResponseResult updateChannelOrderStatus(@RequestParam("status") String status,
        @NotBlank @RequestParam("orderId") String orderId) {

        return orderService.updateChannelOrderStatus(status, orderId);
    }
}
