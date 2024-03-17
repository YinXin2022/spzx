package com.yinxin.pay.service;

import com.yinxin.spzx.model.entity.pay.PaymentInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author YinXin
 * @date 2024-03-17 9:18
 */
@Service
public interface PaymentInfoService {
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Map<String, String> paramMap, Integer payType);
}
