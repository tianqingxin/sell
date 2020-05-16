package com.imooc.sell.service.impl;

import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.converter.OrderMaster2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.dto.ProductDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
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
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.NOPAYED.getCode());
        orderMaster = orderMasterRepository.save(orderMaster);
        //扣库存
        List<ProductDTO> productDTOList = orderDetailList.stream()
                .map(orderDetail ->
                        new ProductDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.delStock(productDTOList);

        BeanUtils.copyProperties(orderMaster, orderDTO);
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
            BeanUtils.copyProperties(orderMaster, orderDTO);
            //根据订单id获取订单详细信息
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
            orderDTO.setOrderDetailList(orderDetailList);
        } else {
            throw new SellException(ResultEnum.PRODUCNT_NOT_FOUND);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> getOrderList(Pageable pageable, String buyerOpenid) {
        //分页获取订单主表信息列表
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.getOrderDTOList(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        String orderId = orderDTO.getOrderId();
        Integer orderStatus = orderDTO.getOrderStatus();
        //判断订单状态，只有新订单才能取消
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单状态】不正确，orderId={},orderStatus={}", orderId, orderStatus);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //更改订单状态
        int num = orderMasterRepository.updateOrderStatusById(orderId, OrderStatusEnum.CANCEL.getCode());
        if (num > 0) {
            orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        List<ProductDTO> productDTOList = orderDTO.getOrderDetailList().stream().
                map(orderDetail -> new ProductDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.addStock(productDTOList);
        //如果买家已支付，则进行退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.PAYED.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】，订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_HAS_FINISHED);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.error("【完结订单】，更新失败，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_UPDATE_FAILURE);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】，订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断订单支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.NOPAYED.getCode())) {
            log.error("【支付订单】，订单支付状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.PAYED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.error("【修改支付状态】，更新失败，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_UPDATE_FAILURE);
        }
        return orderDTO;
    }
}
