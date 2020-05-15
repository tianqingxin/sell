package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    /**
     * 根据商品状态查询符合条件的商品列表
     * @param id
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer id);

    /**
     * 根据商品名称模糊查询符合条件的商品列表
     * @param productName
     * @return
     */
    List<ProductInfo> findByProductNameIsLike(String productName);

}
