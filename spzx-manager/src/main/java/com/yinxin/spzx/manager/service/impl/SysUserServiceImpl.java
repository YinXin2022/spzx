package com.yinxin.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.common.utils.AuthContextUtil;
import com.yinxin.spzx.manager.mapper.SysUserMapper;
import com.yinxin.spzx.manager.service.SysUserService;
import com.yinxin.spzx.model.dto.system.LoginDto;
import com.yinxin.spzx.model.entity.system.SysUser;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.system.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author YinXin
 * @date 2024-01-12 17:24
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;
    private final RedisCache redisCache;

    @Override
    public LoginVo login(LoginDto loginDto) {
        String validateCode = redisCache.getCacheObject(RedisContest.SYS_LOGIN_VALIDATE_CODE + loginDto.getCodeKey(), String.class);
        if (StrUtil.isEmpty(validateCode) || !StrUtil.equalsIgnoreCase(validateCode, loginDto.getCaptcha())) {
            throw new AppException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        SysUser sysUser = sysUserMapper.getByUsername(loginDto.getUserName());
        if (sysUser == null) {
            throw new AppException(ResultCodeEnum.LOGIN_ERROR);
        }
        if (!sysUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
            throw new AppException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisCache.setCacheObject(RedisContest.SYS_LOGIN_USER + token, sysUser,30, TimeUnit.MINUTES);
        redisCache.deleteObject(RedisContest.SYS_LOGIN_VALIDATE_CODE + loginDto.getCodeKey());
        return LoginVo.build(token,null);
    }

    @Override
    public SysUser getUserInfo(String token) {
        return AuthContextUtil.get();
    }

    @Override
    public void logout(String token) {
        redisCache.deleteObject(RedisContest.SYS_LOGIN_USER + token);
    }
}
