package com.imooc.controller;

import com.imooc.converter.OrderVo2OrderDtoConverter;
import com.imooc.dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.ResultVOUtil;
import com.imooc.vo.OrderVo;
import com.imooc.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单controller
 * Created by songyouyu on 2018/5/25
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    // 创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderVo orderVo,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm = {}", orderVo);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderVo2OrderDtoConverter.convert(orderVo);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto orderResult = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    // 订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, pageRequest);
        return ResultVOUtil.success(orderDtoPage);
    }


    // 订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDto);
    }

    // 取消订单
    @PostMapping("cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrderOne(openid , orderId);
        return ResultVOUtil.success();
    }
}
