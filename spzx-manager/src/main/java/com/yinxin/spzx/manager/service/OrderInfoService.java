package com.yinxin.spzx.manager.service;

import com.yinxin.spzx.model.dto.order.OrderStatisticsDto;
import com.yinxin.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-02-22 14:31
 */
@Service
public interface OrderInfoService {
    OrderStatisticsVo getOrderStatistics(OrderStatisticsDto orderStatisticsDto);
}
