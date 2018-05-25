package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songyouyu on 2018/5/25
 */
@Slf4j
public class OrderVo2OrderDtoConverter {

    public static OrderDto convert(OrderVo orderVo) {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderVo.getName());
        orderDto.setBuyerPhone(orderVo.getPhone());
        orderDto.setBuyerAddress(orderVo.getAddress());
        orderDto.setBuyerOpenid(orderVo.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderVo.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        } catch (Exception e) {
            log.error("【对象转换错误】, string = {}", orderVo.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }


}
