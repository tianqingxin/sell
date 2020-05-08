package com.imooc.sell.controller;

import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResponseData;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.pojo.PruductCategory;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.service.PruductCategoryService;
import com.imooc.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private PruductCategoryService pruductCategoryService;

    @GetMapping("/list")
    public ResponseData list() {
        //获取所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findAllUpPro();
        //lambda表达式获取商品类目编号集合
        List<Integer> categoryTypeList=productInfoList.stream().
                map(e -> e.getCategoryType()).collect(Collectors.toList());
        //获取所有上架商品所属的类目
        List<PruductCategory> pruductCategoryList=pruductCategoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (PruductCategory pruductCategory : pruductCategoryList){
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(pruductCategory,productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(pruductCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO =new ProductInfoVO();
                    //将第一个对象的属性拷贝到另一个对象中
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfo(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.successResult(productVOList);
    }
}
