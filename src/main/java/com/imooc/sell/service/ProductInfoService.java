package com.imooc.sell.service;

import com.imooc.sell.dto.ProductDTO;
import com.imooc.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
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

    /**
     * 根据商品Id查询商品信息
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有商品列表
     * @return
     */
    public List<ProductInfo> findAll();

    /**
     * 查询所有上架的商品
     * @return
     */
    public List<ProductInfo> findAllUpPro();

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAllContainPage(Pageable pageable);

    /**
     * 更新或添加商品
     * @param productInfo
     * @return
     */
    ProductInfo savePro(ProductInfo productInfo);

    /**
     * 根据商品id批量查询
     * @param productIdList
     * @return
     */
    List<ProductInfo> findByIdIn(List<String> productIdList);

    /**
     * 增加库存
     * @param productDTOList
     */
    void addStock(List<ProductDTO> productDTOList);
    /**
     * 减少库存
     * @param productDTOList
     */
    void delStock(List<ProductDTO> productDTOList);
}
