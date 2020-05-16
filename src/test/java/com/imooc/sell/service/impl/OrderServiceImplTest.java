package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    private final static String BUYER_OPEN_ID = "112233";

    @Test
    void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("tqx");
        orderDTO.setBuyerAddress("山东济南");
        orderDTO.setBuyerPhone("18766170466");
        orderDTO.setBuyerOpenid(BUYER_OPEN_ID);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("122331");
        orderDetail.setProductQuantity(2);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("sdadsa");
        orderDetail1.setProductQuantity(2);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);
        orderDTO = orderService.createOrder(orderDTO);
        log.info("result={}", orderDTO);
        Assertions.assertNotNull(orderDTO);
    }

    @Test
    void findOrder() {
        OrderDTO orderDTO = orderService.findOrder("2690b42aeab14b3d844d3da8f19eaa0d");
        log.info("orderDTO={}", orderDTO);
        Assertions.assertNotNull(orderDTO);
    }

    @Test
    void getOrderList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderDTO> getOrderList = orderService.getOrderList(pageRequest, BUYER_OPEN_ID);
        log.info("getOrderList={}", getOrderList.getContent());
        Assertions.assertNotEquals(0, getOrderList.getTotalElements());
    }

    @Test
    void cancelOrder() {
        OrderDTO orderDTO = orderService.findOrder("2690b42aeab14b3d844d3da8f19eaa0d");
        OrderDTO result = orderService.cancelOrder(orderDTO);
        log.info("result={}", result);
        Assertions.assertEquals(2, result.getOrderStatus());
    }

    @Test
    void finishOrder() {
        OrderDTO orderDTO = orderService.findOrder("2690b42aeab14b3d844d3da8f19eaa0d");
        OrderDTO result = orderService.finishOrder(orderDTO);
        log.info("result={}", result);
        Assertions.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    void payOrder() {
        OrderDTO orderDTO = orderService.findOrder("2690b42aeab14b3d844d3da8f19eaa0d");
        OrderDTO result = orderService.payOrder(orderDTO);
        log.info("result={}", result);
        Assertions.assertEquals(PayStatusEnum.PAYED.getCode(),result.getPayStatus());
    }
}