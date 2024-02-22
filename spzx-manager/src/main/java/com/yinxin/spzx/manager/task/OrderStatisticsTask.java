package com.yinxin.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.yinxin.spzx.manager.mapper.OrderInfoMapper;
import com.yinxin.spzx.manager.mapper.OrderStatisticsMapper;
import com.yinxin.spzx.model.entity.order.OrderStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YinXin
 * @date 2024-02-22 14:15
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderStatisticsTask {
    private final OrderInfoMapper orderInfoMapper;
    private final OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.findByCreateTime(createTime);
        if (orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
