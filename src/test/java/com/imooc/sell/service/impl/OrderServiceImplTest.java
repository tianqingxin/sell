package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderService.createOrder();
    }

    @Test
    void findOrder() {
    }

    @Test
    void getOrderList() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void finishOrder() {
    }

    @Test
    void payOrder() {
    }
}