package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDto;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyouyu on 2018/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "110110";

    private final String ORDER_ID = "1527064100709125779";

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("song");
        orderDto.setBuyerAddress("earth");
        orderDto.setBuyerPhone("12345678912");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123478");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(4);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("创建订单：result -> {}", result);

    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        log.info("查询单个订单：result -> {}", orderDto);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDto> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        log.info("查询订单列表：result -> {}", orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}