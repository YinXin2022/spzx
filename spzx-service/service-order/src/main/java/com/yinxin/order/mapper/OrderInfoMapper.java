package com.yinxin.order.mapper;

import com.yinxin.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-12 15:44
 */
@Mapper
public interface OrderInfoMapper {
    void save(OrderInfo orderInfo);

    OrderInfo findByOrderId(Long orderId);

    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
}
