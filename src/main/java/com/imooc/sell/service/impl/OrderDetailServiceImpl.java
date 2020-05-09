package com.imooc.sell.service.impl;

import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public void deleteByOrderId(String orderId) {
        orderDetailRepository.deleteByOrderId(orderId);
    }

    @Override
    @Transactional
    public void updateById(String orderId, String detailId) {
        orderDetailRepository.updateById(orderId,detailId);
    }

    @Override
    @Transactional
    public void deleteById(String detailId) {
        orderDetailRepository.deleteById(detailId);
    }
}
