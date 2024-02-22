package com.yinxin.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yinxin.spzx.manager.mapper.OrderStatisticsMapper;
import com.yinxin.spzx.manager.service.OrderInfoService;
import com.yinxin.spzx.model.dto.order.OrderStatisticsDto;
import com.yinxin.spzx.model.entity.order.OrderStatistics;
import com.yinxin.spzx.model.vo.order.OrderStatisticsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author YinXin
 * @date 2024-02-22 14:32
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {
    private final OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatistics(OrderStatisticsDto orderStatisticsDto) {
        List<OrderStatistics> orderStatistics = orderStatisticsMapper.findByDto(orderStatisticsDto);
        Map<Date, BigDecimal> collect = orderStatistics.stream().collect(Collectors.toMap(OrderStatistics::getOrderDate, OrderStatistics::getTotalAmount));
        for (Map.Entry e : collect.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
        //日期列表
        List<String> dateList = orderStatistics.stream().map(o -> DateUtil.format(o.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());

        //统计金额列表
        List<BigDecimal> amountList = orderStatistics.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        return orderStatisticsVo;
    }
}
