package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by songyouyu on 2018/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("110110");
        orderDetail.setProductIcon("http://xxxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(2);

        orderDetailRepository.save(orderDetail);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("110110");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}