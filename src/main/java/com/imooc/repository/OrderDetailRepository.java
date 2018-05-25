package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by songyouyu on 2018/5/23
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String > {

    List<OrderDetail> findByOrderId(String orderId);
}
