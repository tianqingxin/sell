package com.imooc.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 表单验证
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名不允许为空")
    private String name;
    /**
     * 买家手机号
     */
    @NotEmpty(message = "买家手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$"
            , message = "手机号格式不正确")
    private String phone;
    /**
     * 微信号id
     */
    @NotEmpty(message = "微信号id不能为空")
    private String openid;

    /**
     * 地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
