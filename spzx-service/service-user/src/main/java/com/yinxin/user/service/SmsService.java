package com.yinxin.user.service;

import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-02-29 19:56
 */
@Service
public interface SmsService {
    void sendValidateCode(String phone);
}
