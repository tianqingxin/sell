package com.imooc.sell.controller;

import com.google.common.collect.Maps;
import com.imooc.sell.VO.ResponseData;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * 买家订单api
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
@Validated
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResponseData<Map<String, Object>> createOrder(@Valid OrderForm orderForm,
                                                         BindingResult bindingResult) {
        //判断表单信息是否正确
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】不正确，订单信息：{}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】订单详情无商品，orderDetail={}", orderDTO.getOrderDetailList());
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO result = orderService.createOrder(orderDTO);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("orderId", result.getOrderId());
        return ResultVOUtils.successResult(resultMap);
    }

    /**
     * 订单列表
     *
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResponseData<List<OrderDTO>> getOrderList(@RequestParam("openid")@NotEmpty(message = "参数openid不允许为空") String openid,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<OrderDTO> orderDTOPage = orderService.getOrderList(PageRequest.of(page, size), openid);
        return ResultVOUtils.successResult(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     *
     * @param orderId 订单id
     * @return
     */
    @GetMapping("/detail")
    public ResponseData<OrderDTO> getOrderDetailList(@RequestParam("orderId") @NotEmpty(message = "orderId不允许为空") String orderId,
                                                     @RequestParam("openid") @NotEmpty(message = "openid不允许为空") String openid) {
        OrderDTO orderDTO = buyerService.findOrderOne(orderId,openid);
        return ResultVOUtils.successResult(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResponseData cancelOrder(@RequestParam("orderId") @NotEmpty(message = "orderId不允许为空") String orderId,
                                    @RequestParam("openid") @NotEmpty(message = "openid不允许为空") String openid) {
        buyerService.cancelOrder(orderId,openid);
        return ResultVOUtils.success();
    }
}
