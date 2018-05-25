package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductCategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtil;
import com.imooc.vo.ProductInfoVo;
import com.imooc.vo.ProductVo;
import com.imooc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品controller
 * Created by songyouyu on 2018/5/22
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;


    @GetMapping("/list")
    public ResultVo list() {

        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 查询类目(一次性查询)
        // 传统方法
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).
                collect(Collectors.toList());
        List<ProductCategory> productCategoryList =
                productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    productInfoVo.setProductId(productInfo.getProductId());
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setProductPrice(productInfo.getProductPrice());
                    productInfoVo.setProductDescription(productInfo.getProductDescription());
                    productInfoVo.setProductIcon(productInfo.getProductIcon());

                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVOUtil.success(productVoList);
    }
}
