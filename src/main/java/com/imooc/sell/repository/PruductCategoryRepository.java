package com.imooc.sell.repository;

import com.imooc.sell.pojo.PruductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PruductCategoryRepository extends JpaRepository<PruductCategory,Integer> {
    /**
     * 通过类目编号查询符合条件的类目
     * @param list
     * @return
     */
    List<PruductCategory> findByCategoryTypeIn(List<Integer> list);
}
