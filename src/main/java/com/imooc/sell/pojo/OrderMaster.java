package com.imooc.sell.pojo;

import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单主信息
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {
    /**
     * 订单id
     */
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /**
     * 订单支付状态：0、未支付
     */
    private Integer payStatus= PayStatusEnum.NOPAYED.getCode();

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
