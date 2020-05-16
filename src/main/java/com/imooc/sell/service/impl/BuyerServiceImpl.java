package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if(orderDTO ==null){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);
    }

    /**
     * 查询订单中微信信息和用户微信是否一致
     *
     * @param openid
     * @param orderId
     * @return
     */
    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOrder(orderId);
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        return orderDTO;
    }
}
