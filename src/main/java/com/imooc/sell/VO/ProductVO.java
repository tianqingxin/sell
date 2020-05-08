package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品（包含类目）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    /**
     *类目名称
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 类目编号
     */
    @JsonProperty("type")
    private Integer categoryType;
    /**
     * 商品信息列表
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfo;
}
