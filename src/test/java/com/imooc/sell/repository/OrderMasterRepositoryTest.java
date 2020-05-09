package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Slf4j
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    void findByBuyerOpenid() {
        String openId = "18766170465";
        PageRequest pageRequest = PageRequest.of(1, 10);
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(openId, pageRequest);
        //分页后需判断查询出的总数量不为零即可作为通过测试的依据
        Assertions.assertNotEquals(0, orderMasterPage.getTotalElements());
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();
        log.info("orderMasterList={}", orderMasterList);
    }

    @Test
    void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(StringUtils.getUuid());
        orderMaster.setBuyerOpenid("18766170465");
        orderMaster.setBuyerName("刘丽");
        orderMaster.setBuyerPhone("18766170465");
        orderMaster.setBuyerAddress("山东济南");
        orderMaster.setOrderAmount(new BigDecimal(21));
        orderMaster = orderMasterRepository.save(orderMaster);
        Assertions.assertEquals("18766170465", orderMaster.getBuyerPhone());
        log.info("orderMaster={}", orderMaster);
    }

    @Test
    void delete() {
        orderMasterRepository.deleteById("sdasdsa");
    }
}