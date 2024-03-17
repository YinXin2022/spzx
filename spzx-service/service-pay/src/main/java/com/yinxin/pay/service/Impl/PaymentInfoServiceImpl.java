package com.yinxin.pay.service.Impl;

import com.alibaba.fastjson.JSON;
import com.yinxin.pay.mapper.PaymentInfoMapper;
import com.yinxin.pay.service.PaymentInfoService;
import com.yinxin.spzx.model.entity.order.OrderInfo;
import com.yinxin.spzx.model.entity.order.OrderItem;
import com.yinxin.spzx.model.entity.pay.PaymentInfo;
import com.yinxin.spzx.order.client.OrderFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author YinXin
 * @date 2024-03-17 9:19
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {
    private final OrderFeignClient orderFeignClient;
    private final PaymentInfoMapper paymentInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentInfo savePaymentInfo(String orderNo) {
        // 查询支付信息数据，如果已经已经存在了就不用进行保存(一个订单支付失败以后可以继续支付)
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);
        if (null == paymentInfo) {
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            String content = "";
            for (OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }

    @Override
    public void updatePaymentStatus(Map<String, String> map, Integer payType) {
        // 查询PaymentInfo
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(map.get("out_trade_no"));
        if (paymentInfo.getPaymentStatus() == 1) {
            return;
        }
        //更新支付信息
        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(map.get("trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(map));
        paymentInfoMapper.updateById(paymentInfo);
        //更新订单状态
        orderFeignClient.updateOrderStatus(paymentInfo.getOrderNo(), payType);
    }
}
