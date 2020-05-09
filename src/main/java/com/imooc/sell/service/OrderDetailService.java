package com.imooc.sell.service;

import com.imooc.sell.pojo.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    /**
     * 根据订单id查询订单详情列表
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

    /**
     * 根据订单id删除订单
     * @param orderId
     */
    void deleteByOrderId(String orderId);

    /**
     * 根据详情id更新订单id
     * @param orderId
     * @param detailId
     */
    void updateById(String orderId, String detailId);

    void deleteById(String detailId);

}
