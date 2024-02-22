package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-02-22 14:20
 */
@Mapper
public interface OrderInfoMapper {
    OrderStatistics findByCreateTime(String createTime);
}
