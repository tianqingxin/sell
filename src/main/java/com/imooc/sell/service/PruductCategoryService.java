package com.imooc.sell.service;

import com.imooc.sell.pojo.PruductCategory;

import java.util.List;

public interface PruductCategoryService {
    List<PruductCategory> findAll();
    PruductCategory findById(Integer categoryId);
    List<PruductCategory> findByCategoryTypeIn(List<Integer> list);
    PruductCategory save(PruductCategory pruductCategory);
}
