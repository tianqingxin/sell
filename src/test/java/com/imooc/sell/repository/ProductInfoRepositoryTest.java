package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    void findByProductStatus() {
        List<ProductInfo> list= productInfoRepository.findByProductStatus(0);
        log.info("list={}",list);
    }
    @Test
    void insertOneTest(){
        ProductInfo productInfo = new ProductInfo("sdadsa",
                "皮蛋瘦肉粥",new BigDecimal(3.14),100,"好喝","icon",1,0);
        productInfo=productInfoRepository.save(productInfo);
        Assertions.assertEquals(1,productInfo.getCategoryType());
        log.info("productInfo={}",productInfo);
    }

    @Test
    void findByProductNameIsLike() {
        List<ProductInfo> productInfoList=productInfoRepository.findByProductNameIsLike("%"+"皮蛋"+"%");
        Assertions.assertEquals(1,productInfoList.size());
        log.info("productInfoList={}",productInfoList);
    }
}