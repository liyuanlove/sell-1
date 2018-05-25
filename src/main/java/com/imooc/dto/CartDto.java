package com.imooc.dto;

import lombok.Data;

/**
 * Created by songyouyu on 2018/5/23
 */
@Data
public class CartDto {

    /** 商品id */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
