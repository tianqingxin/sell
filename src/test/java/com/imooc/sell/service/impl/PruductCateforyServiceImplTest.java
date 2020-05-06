package com.imooc.sell.service.impl;

import com.imooc.sell.pojo.PruductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class PruductCateforyServiceImplTest {
    @Autowired
    private PruductCateforyServiceImpl pruductCateforyService;

    @Test
    void findAll() {
        List<PruductCategory> pruductCateforyList=pruductCateforyService.findAll();
        Assertions.assertNotEquals(0,pruductCateforyList.size());
        log.info("pruductCateforyList={}",pruductCateforyList);
    }

    @Test
    void findById() {
        PruductCategory pruductCategory=pruductCateforyService.findById(1);
        Assertions.assertEquals(1,pruductCategory.getCategoryId());
        log.info("pruductCategory={}",pruductCategory);
    }

    @Test
    void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2,3);
        List<PruductCategory> pruductCategoryList=pruductCateforyService.findByCategoryTypeIn(list);
        Assertions.assertNotEquals(0,pruductCategoryList.size());
        log.info("pruductCategoryList={}",pruductCategoryList);
    }

    @Test
    void save() {
        PruductCategory pruductCategory = new PruductCategory("男生最爱",4);
        pruductCateforyService.save(pruductCategory);
        Assertions.assertNotNull(pruductCategory);
        log.info("saved pruductCategory={}",pruductCategory);
    }
}