package com.imooc.sell.service.impl;

import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findByProductStatus(Integer id) {
        return productInfoRepository.findByProductStatus(id);
    }

    @Override
    public List<ProductInfo> findByProductNameIsLike(String productName) {
        return productInfoRepository.findByProductNameIsLike("%" + productName + "%");
    }

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(productId);
        if (productInfoOptional != null && productInfoOptional.isPresent()) {
            return productInfoOptional.get();
        }
        return null;
    }
    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public List<ProductInfo> findAllUpPro() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAllContainPage(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo savePro(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}
