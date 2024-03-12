package com.yinxin.order.mapper;

import com.yinxin.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-12 15:46
 */
@Mapper
public interface OrderItemMapper {
    void save(OrderItem orderItem);

    List<OrderItem> findByOrderId(Long id);
}
