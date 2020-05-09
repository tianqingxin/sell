package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态枚举
 */
@AllArgsConstructor
@Getter
public enum PayStatusEnum {
    PAYED(1,"已支付"),
    NOPAYED(0,"未支付");
    private Integer code;
    private String msg;
}
