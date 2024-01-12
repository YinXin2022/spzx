package com.yinxin.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.spzx.manager.service.ValidateCodeService;
import com.yinxin.spzx.model.vo.system.ValidateCodeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author YinXin
 * @date 2024-01-12 20:43
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidateCodeServiceImpl implements ValidateCodeService {
    private final RedisCache redisCache;

    @Override
    public ValidateCodeVo generateValidateCode() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);

        String codeKey = UUID.randomUUID().toString().replace("-", "");
        redisCache.setCacheObject(RedisContest.SYS_LOGIN_VALIDATE_CODE + codeKey, circleCaptcha.getCode(), 2, TimeUnit.MINUTES);

        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue("data:image/png;base64," + circleCaptcha.getImageBase64());
        return validateCodeVo;
    }
}
