package com.imooc.service;

import com.imooc.dto.OrderDto;

/**
 * 买家
 * Created by songyouyu on 2018/5/25
 */
public interface BuyerService {

    // 查询一个订单
    OrderDto findOrderOne(String openid, String orderId);

    // 取消一个订单
    OrderDto cancelOrderOne(String openid, String orderId);
}
