package com.imooc.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class OrderDetailServiceImplTest {

    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Test
    void findByOrderId() {
    }

    @Test
    void deleteByOrderId() {
        orderDetailService.deleteByOrderId("sdasdsa");
    }

    @Test
    void deleteById(){
        orderDetailService.deleteById("sdas");
    }

    @Test
    void updateById() {
        orderDetailService.updateById("18766170465","sdas");
    }
}