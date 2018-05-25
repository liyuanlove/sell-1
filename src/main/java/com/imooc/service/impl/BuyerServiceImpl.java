package com.imooc.service.impl;

import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by songyouyu on 2018/5/25
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDto cancelOrderOne(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (orderDto == null) {
            log.error("【查询订单】查询不到该订单，orderId = {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null) {
            return null;
        }
        if (! orderDto.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致， openid = {}, orderDto = {}", openid, orderDto);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
