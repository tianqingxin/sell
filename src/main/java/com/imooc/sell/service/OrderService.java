package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    OrderDTO findOrder(String orderId);

    /**
     * 分页查询订单列表
     * @param pageable
     * @param buyerOpenid
     * @return
     */
    Page<OrderDTO> getOrderList(Pageable pageable,String buyerOpenid);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    OrderDTO cancelOrder(OrderDTO orderDTO);

    /**
     * 完成订单
     * @param orderDTO
     * @return
     */
    OrderDTO finishOrder(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    OrderDTO payOrder(OrderDTO orderDTO);


//    OrderDTO findOrderByOrderIdAndOpenid(String orderId, String openid);
}
