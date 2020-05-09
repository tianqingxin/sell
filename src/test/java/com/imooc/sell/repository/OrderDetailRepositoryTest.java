package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    void findByOrOrderId() {
        String orderId = "111222322";
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        log.info("orderDetailList={}", orderDetailList);
    }

    @Test
    @Transactional
    void delete() {
        orderDetailRepository.deleteByOrderId("sdasdsa");
    }

    @Test
    @Transactional
    void updateById() {
        orderDetailRepository.updateById("18766170465", "sdas");
    }

    @Test
    void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(StringUtils.getUuid());
        orderDetail.setOrderId("111222322");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("123");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(2.5));
        orderDetail.setProductQuantity(1);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(result);
    }
}