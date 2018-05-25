package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by songyouyu on 2018/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "110110";

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("songyouyu");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("earth");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(18.2));

        OrderMaster master = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(master);

    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0, 1);
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(OPENID, request);
        System.out.println(orderMasterPage.getTotalElements());
    }
}