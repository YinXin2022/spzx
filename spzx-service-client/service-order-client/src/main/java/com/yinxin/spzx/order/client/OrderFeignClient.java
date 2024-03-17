package com.yinxin.spzx.order.client;

import com.yinxin.spzx.model.entity.order.OrderInfo;
import com.yinxin.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YinXin
 * @date 2024-03-17 9:08
 */
@FeignClient("service-order")
public interface OrderFeignClient {
    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo);

    @GetMapping("/api/order/orderInfo/auth/updateOrderStatusPayed/{orderNo}/{payType}")
    Result updateOrderStatus(@PathVariable String orderNo, @PathVariable Integer payType);
}
