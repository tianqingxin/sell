package com.imooc.sell.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@ToString
@NoArgsConstructor
public class ProductInfo {
    @Id
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
     * 库存
     */
    private Integer productStock;
    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品图标
     */
    private String productIcon;
    /**
     * 类目编号
     */
    private Integer categoryType;

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer categoryType, Integer productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoryType = categoryType;
        this.productStatus = productStatus;
    }

    /**
     * 商品状态：0、正常；1、下架
     */
    private Integer productStatus;
}
