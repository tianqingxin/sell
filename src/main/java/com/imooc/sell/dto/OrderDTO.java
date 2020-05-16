package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) //过滤规则，
// 这里是只返回不为null的数据,全局配置可在yml文件中统一配置
public class OrderDTO {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 买家名称
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信ID
     */
    private String buyerOpenid;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态:0、新下单，默认为新下单
     */
    private Integer orderStatus;
    /**
     * 订单支付状态：0、未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    /**
     * 订单详情列表
     */
    private List<OrderDetail> orderDetailList;
}
