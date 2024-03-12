package com.yinxin.order.mapper;

import com.yinxin.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-03-12 15:46
 */
@Mapper
public interface OrderLogMapper {
    void save(OrderLog orderLog);
}
