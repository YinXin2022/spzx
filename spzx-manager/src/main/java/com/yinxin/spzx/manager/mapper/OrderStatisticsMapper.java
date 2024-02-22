package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.dto.order.OrderStatisticsDto;
import com.yinxin.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-22 14:20
 */
@Mapper
public interface OrderStatisticsMapper {
    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> findByDto(OrderStatisticsDto orderStatisticsDto);
}
