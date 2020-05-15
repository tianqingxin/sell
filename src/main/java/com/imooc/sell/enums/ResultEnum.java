package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    PRODUCNT_NOT_FOUND(10,"商品不存在"),
    PRODUCT_INFO_ERROR(20,"商品信息错误，库存不足");
    private Integer code;
    private String  msg;
}
