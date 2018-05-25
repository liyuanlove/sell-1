package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品service
 * Created by songyouyu on 2018/5/22
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /** 查询所有在架商品列表 */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /** 添加库存 */
    void increaseStock(List<CartDto> cartDtoList);

    /** 减少库存 */
    void decreaseStock(List<CartDto> cartDtoList);
}
