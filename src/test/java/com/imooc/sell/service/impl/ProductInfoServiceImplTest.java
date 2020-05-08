package com.imooc.sell.service.impl;

import com.imooc.sell.pojo.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    void findByProductStatus() {
        List<ProductInfo> productInfoList= productInfoService.findByProductStatus(0);
        log.info("productInfoList={}",productInfoList);
    }

    @Test
    void findByProductNameIsLike() {
        List<ProductInfo> productInfoList=productInfoService.findByProductNameIsLike("瘦肉");
        Assertions.assertEquals(1,productInfoList.size());
        log.info("like productInfoList={}",productInfoList);
    }

    @Test
    void findOne() {
        ProductInfo productInfo=productInfoService.findOne("sdadsa");
        Assertions.assertEquals("sdadsa",productInfo.getProductId());
        log.info("findOne productInfo={}",productInfo);
    }

    @Test
    void findAll() {
        List<ProductInfo> productInfoList=productInfoService.findAll();
        Assertions.assertNotEquals(0,productInfoList.size());
        log.info("findAll productInfoList={}",productInfoList);
    }

    @Test
    void findAllUpPro() {
        List<ProductInfo> productInfoList=productInfoService.findAllUpPro();
        Assertions.assertNotEquals(0,productInfoList.size());
        log.info("findAllUpPro productInfoList={}",productInfoList);
    }

    @Test
    void findAllContainPage() {
        Page<ProductInfo> productInfoPage=productInfoService.findAllContainPage(PageRequest.of(0,10));
        List<ProductInfo> productInfoList=productInfoPage.getContent();
        log.info("productInfoPage.get()={}",productInfoList);
    }

    @Test
    void savePro() {
//        ProductInfo productInfo = new ProductInfo("sdadsa",
//                "皮蛋瘦肉粥",new BigDecimal(3.14),100,"好喝","icon",1,0);

        ProductInfo productInfo=productInfoService.findOne("sdadsa");
        productInfo.setProductPrice(new BigDecimal(2.80));
        productInfoService.savePro(productInfo);
    }
}