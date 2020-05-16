package com.imooc.sell.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail {
    /**
     * 订单详情id
     */
    @Id
    private String detailId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品数量
     */
    private Integer productQuantity;
    /**
     * 商品图标
     */
    private String productIcon;
    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
