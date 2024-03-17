package com.yinxin.pay.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.yinxin.common.exception.AppException;
import com.yinxin.pay.appconfig.AlipayProperties;
import com.yinxin.pay.service.AlipayService;
import com.yinxin.pay.service.PaymentInfoService;
import com.yinxin.spzx.model.entity.pay.PaymentInfo;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author YinXin
 * @date 2024-03-17 10:22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlipayServiceImpl implements AlipayService {
    private final AlipayClient alipayClient;
    private final PaymentInfoService paymentInfoService;
    private final AlipayProperties alipayProperties;

    @SneakyThrows
    @Override
    public String submitAlipay(String orderNo) {
        //保存支付记录
        PaymentInfo paymentInfo = paymentInfoService.savePaymentInfo(orderNo);
        //创建API对应的request
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        // 同步回调
        alipayRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());
        // 异步回调
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyPaymentUrl());

        // 准备请求参数 ，声明一个map 集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("out_trade_no", paymentInfo.getOrderNo());
        map.put("product_code", "QUICK_WAP_WAY");
        //map.put("total_amount",paymentInfo.getAmount());
        map.put("total_amount", new BigDecimal("0.01"));
        map.put("subject", paymentInfo.getContent());
        alipayRequest.setBizContent(JSON.toJSONString(map));

        // 发送请求
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayRequest);
        if (response.isSuccess()) {
            log.info("调用成功");
            return response.getBody();
        } else {
            log.info("调用失败");
            throw new AppException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
