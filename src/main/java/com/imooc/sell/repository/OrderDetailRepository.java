package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);

    void deleteByOrderId(String orderId);

    @Modifying
    @Query("update OrderDetail set orderId=:orderId where detailId=:detailId")
    void updateById(@Param("orderId") String orderId,@Param("detailId") String detailId);
}
