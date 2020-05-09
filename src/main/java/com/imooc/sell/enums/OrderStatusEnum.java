package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 */
@AllArgsConstructor
@Getter
public enum  OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完成订单"),
    CANCEL(2,"取消订单");

    private Integer code;
    private String msg;
}
