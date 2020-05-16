package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

public interface BuyerService {
    /**
     * 查询订单
     * @param openid
     * @param orderId
     * @return
     */
    public OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    public OrderDTO cancelOrder(String openid,String orderId);
}
