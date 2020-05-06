package com.imooc.sell.service.impl;

import com.imooc.sell.pojo.PruductCategory;
import com.imooc.sell.repository.PruductCategoryRepository;
import com.imooc.sell.service.PruductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PruductCateforyServiceImpl implements PruductCategoryService {
    @Autowired
    private PruductCategoryRepository pruductCategoryRepository;
    @Override
    public List<PruductCategory> findAll() {
        return pruductCategoryRepository.findAll();
    }

    @Override
    public PruductCategory findById(Integer categoryId) {
        Optional<PruductCategory> optional= pruductCategoryRepository.findById(categoryId);
       if(optional !=null && optional.isPresent()){
           return optional.get();
       }
        return null;
    }

    @Override
    public List<PruductCategory> findByCategoryTypeIn(List<Integer> list) {

        return pruductCategoryRepository.findByCategoryTypeIn(list);
    }

    @Override
    public PruductCategory save(PruductCategory pruductCategory) {
        return pruductCategoryRepository.save(pruductCategory);
    }
}
