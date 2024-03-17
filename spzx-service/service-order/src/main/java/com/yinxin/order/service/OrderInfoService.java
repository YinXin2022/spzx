package com.yinxin.order.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.dto.h5.OrderInfoDto;
import com.yinxin.spzx.model.entity.order.OrderInfo;
import com.yinxin.spzx.model.vo.h5.TradeVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-03-11 18:56
 */
@Service
public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo);

    void updateOrderStatus(String orderNo, Integer payType);
}
