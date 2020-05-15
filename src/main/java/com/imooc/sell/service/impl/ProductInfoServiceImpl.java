package com.imooc.sell.service.impl;

import com.imooc.sell.dto.ProductDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductInfo> findByIdIn(List<String> productIdList) {
        return productInfoRepository.findAllById(productIdList);
    }

    @Override
    public void addStock(List<ProductDTO> productDTOList) {
        List<String> productIdList = productDTOList.stream().map(productDTO -> productDTO.getProductId())
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productInfoRepository.findAllById(productIdList);
        for (ProductDTO productDTO : productDTOList) {
            String productDTOId = productDTO.getProductId();
            for (ProductInfo productInfo : productInfoList) {
                String productId = productInfo.getProductId();
                if (productDTOId.equals(productId)) {
                    Integer remainStock = productInfo.getProductStock() + productDTO.getProductQuantity();
                    if(remainStock <0){
                        throw new SellException(ResultEnum.PRODUCNT_NOT_FOUND);
                    }
                    productInfo.setProductStock(remainStock);
                }
            }
        }
        productInfoRepository.saveAll(productInfoList);
    }

    @Override
    @Transactional
    public void delStock(List<ProductDTO> productDTOList) {
        List<String> productIdList = productDTOList.stream().map(productDTO -> productDTO.getProductId())
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productInfoRepository.findAllById(productIdList);
        for (ProductDTO productDTO : productDTOList) {
            String productDTOId = productDTO.getProductId();
            for (ProductInfo productInfo : productInfoList) {
                String productId = productInfo.getProductId();
                if (productDTOId.equals(productId)) {
                    Integer remainStock = productInfo.getProductStock() - productDTO.getProductQuantity();
                    if(remainStock <0){
                        throw new SellException(ResultEnum.PRODUCNT_NOT_FOUND);
                    }
                    productInfo.setProductStock(remainStock);
                }
            }
        }
        productInfoRepository.saveAll(productInfoList);
    }
}
