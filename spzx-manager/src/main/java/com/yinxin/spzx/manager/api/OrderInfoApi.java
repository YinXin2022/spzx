package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.OrderInfoService;
import com.yinxin.spzx.model.dto.order.OrderStatisticsDto;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YinXin
 * @date 2024-02-22 14:30
 */
@Tag(name = "订单信息接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/orderInfo")
public class OrderInfoApi {
    private final OrderInfoService orderInfoService;

    @Operation(summary = "获取订单统计信息")
    @GetMapping("/statistics")
    public Result getOrderStatistics(OrderStatisticsDto orderStatisticsDto) {
        return Result.build(orderInfoService.getOrderStatistics(orderStatisticsDto), ResultCodeEnum.SUCCESS);
    }
}
