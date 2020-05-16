package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update OrderMaster o set o.orderStatus=:orderStatus where o.orderId=:orderId")
    int updateOrderStatusById(@Param("orderId") String orderId,@Param("orderStatus") Integer orderStatus);
}
