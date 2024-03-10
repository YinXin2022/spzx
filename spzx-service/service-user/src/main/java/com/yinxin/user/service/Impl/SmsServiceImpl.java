package com.yinxin.user.service.Impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.user.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author YinXin
 * @date 2024-02-29 19:56
 */
@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final RedisCache redisCache;

    @Override
    public void sendValidateCode(String phone) {
        String code = redisCache.getCacheObject(RedisContest.PHONE_CODE + phone, String.class);
        if (StringUtils.hasText(code)) {
            return;
        }
        String validateCode = RandomStringUtils.randomNumeric(4);
        redisCache.setCacheObject(RedisContest.PHONE_CODE + phone, validateCode, 1, TimeUnit.MINUTES);
        log.info("发送验证码成功，验证码为：{}", validateCode);
    }
}
