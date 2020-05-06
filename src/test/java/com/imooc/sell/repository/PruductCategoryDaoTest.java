package com.imooc.sell.repository;

import com.imooc.sell.pojo.PruductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class PruductCategoryDaoTest {
    @Autowired
    private PruductCategoryRepository pruductCategoryDao;

    @Test
    void findOne() {
        PruductCategory pruductCategory=null;
        Optional<PruductCategory> optional= pruductCategoryDao.findById(1);

        if(optional !=null && optional.isPresent()){
            pruductCategory = optional.get();
        }
        log.info("类目信息：{}",pruductCategory.toString());
    }

    @Test
    void addOne(){
        PruductCategory pruductCategory = new PruductCategory();
        pruductCategory.setCategoryName("主食");
        pruductCategory.setCategoryType(1);
        pruductCategoryDao.save(pruductCategory);
        log.info("实体信息：{}",pruductCategory);
    }
    @Test
    void updateOne(){
        PruductCategory pruductCategory=null;
        Optional<PruductCategory> optional= pruductCategoryDao.findById(3);

        if(optional !=null && optional.isPresent()){
            pruductCategory = optional.get();
        }
        pruductCategory.setCategoryType(10);
        pruductCategoryDao.save(pruductCategory);
        log.info("实体信息：{}",pruductCategory);
    }

    @Test
    public void findCategory(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<PruductCategory> pruductCategoryList=pruductCategoryDao.findByCategoryTypeIn(list);
        Assertions.assertNotEquals(0,pruductCategoryList.size());
        log.info("pruductCategoryList={}",pruductCategoryList);
    }
}