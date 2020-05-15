package com.imooc.sell.service.impl;

import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.dto.ProductDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = StringUtils.getUuid();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> productIdList = orderDetailList.stream().map(orderDetail ->
                (orderDetail.getProductId())).collect(Collectors.toList());
        //查询商品价格、数量
        List<ProductInfo> productInfoList = productInfoService.findByIdIn(productIdList);
        if (productInfoList.isEmpty()) {
            throw new SellException(ResultEnum.PRODUCNT_NOT_FOUND);
        }
        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setDetailId(StringUtils.getUuid());
            orderDetail.setOrderId(orderId);
            String orderDetailProductId = orderDetail.getProductId();
            for (ProductInfo productInfo : productInfoList) {
                String productInfoId = productInfo.getProductId();
                if (orderDetailProductId.equals(productInfoId)) {
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                }
            }
        }
        //订单写入数据库
        List<OrderDetail> orderDetails = orderDetailRepository.saveAll(orderDetailList);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster = orderMasterRepository.save(orderMaster);
        //扣库存
        List<ProductDTO> productDTOList = orderDetailList.stream()
                .map(orderDetail ->
                        new ProductDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.delStock(productDTOList);

        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public OrderDTO findOrder(String orderId) {
        OrderDTO orderDTO = new OrderDTO();
        //根据订单id获取订单主表信息
        Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findById(orderId);
        if (optionalOrderMaster != null && optionalOrderMaster.isPresent()) {
            OrderMaster orderMaster = optionalOrderMaster.get();
            log.info("orderMaster={}", orderMaster);
            BeanUtils.copyProperties(orderMaster, orderDTO);
            //根据订单id获取订单详细信息
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
            log.info("orderDetailList={}", orderDetailList);
            orderDTO.setOrderDetailList(orderDetailList);
            log.info("orderDTO={}", orderDTO);
        }

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> getOrderList(Pageable pageable, String buyerOpenid) {
//        Page<OrderDTO> orderDTOPage = new pa
//        //分页获取订单主表信息列表
//        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
//        BeanUtils.copyProperties(orderMasterPage,);
        return null;
    }

    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {
        return null;
    }
}
