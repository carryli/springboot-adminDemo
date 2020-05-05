package com.cloud.channel.backend.business.objects.param;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author Bruce
 * @classname PaymentInfoParam
 * @description 支付信息参数
 * @date 2020/5/5 0005 10:52
 */
@Data
public class PaymentInfoParam implements Serializable {

    private static final long serialVersionUID = 7474005441539120243L;
    /**
     * 主键id
     */
    private String id;

    /**
     * 实名
     */
    @NotBlank
    private String realName;

    /**
     * 账号
     */
    @NotBlank
    private String account;

    /**
     * 开户银行
     */
    @NotBlank
    private String bankName;

    /**
     * 开户支行地址
     */
    @NotBlank
    private String depositBankAddress;
}
