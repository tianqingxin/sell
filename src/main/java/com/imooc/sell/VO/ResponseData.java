package com.imooc.sell.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 请求返回值对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
}
