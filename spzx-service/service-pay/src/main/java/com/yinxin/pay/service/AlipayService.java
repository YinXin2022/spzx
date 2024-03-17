package com.yinxin.pay.service;

import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-03-17 10:21
 */
@Service
public interface AlipayService {
    String submitAlipay(String orderNo);
}
