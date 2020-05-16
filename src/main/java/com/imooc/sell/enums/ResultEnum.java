package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    PRODUCNT_NOT_FOUND(10,"商品不存在"),
    PRODUCT_INFO_ERROR(20,"商品信息错误，库存不足"),
    ORDER_DETAIL_NOT_EXIST(30,"订单详情不存在"),
    ORDER_STATUS_ERROR(500,"订单状态不正确"),
    ORDER_HAS_FINISHED(40,"订单已完成"),
    ORDER_STATUS_UPDATE_FAILURE(50,"订单更新失败"),
    ORDER_PAY_STATUS_ERROR(60,"订单支付状态不正确");
    private Integer code;
    private String  msg;
}
