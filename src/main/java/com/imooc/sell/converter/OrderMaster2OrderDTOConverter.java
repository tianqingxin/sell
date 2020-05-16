package com.imooc.sell.converter;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {

    private static OrderDTO convertDTO(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> getOrderDTOList(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(orderMaster -> convertDTO(orderMaster)).collect(Collectors.toList());
    }
}
