package com.imooc.sell.converter;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());
        List<OrderDetail> orderDetailList = Lists.newArrayList();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【类型转换】异常,orderDetail={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        } finally {

        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
