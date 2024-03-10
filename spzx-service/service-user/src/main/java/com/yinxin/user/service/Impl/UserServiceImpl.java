package com.yinxin.user.service.Impl;

import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.common.utils.AssertUtil;
import com.yinxin.common.utils.AuthContextUtil;
import com.yinxin.spzx.model.dto.h5.UserLoginDto;
import com.yinxin.spzx.model.dto.h5.UserRegisterDto;
import com.yinxin.spzx.model.entity.user.UserInfo;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.h5.UserInfoVo;
import com.yinxin.user.mapper.UserInfoMapper;
import com.yinxin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author YinXin
 * @date 2024-03-10 13:54
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RedisCache redisCache;
    private final UserInfoMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterDto userRegisterDto) {
        userRegisterDto.check();

        String code = redisCache.getCacheObject(RedisContest.PHONE_CODE + userRegisterDto.getUsername(), String.class);
        AssertUtil.isTrueThrow(!code.equals(userRegisterDto.getCode()), () -> new AppException(ResultCodeEnum.VALIDATECODE_ERROR));

        UserInfo userInfo = userMapper.getByUsername(userRegisterDto.getUsername());
        AssertUtil.isTrueThrow(null != userInfo, () -> new AppException(ResultCodeEnum.USER_NAME_IS_EXISTS));

        UserInfo user = userRegisterDto.dto2entity();
        userMapper.save(user);
        redisCache.deleteObject(RedisContest.PHONE_CODE + userRegisterDto.getUsername());
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        UserInfo userInfo = userMapper.getByUsername(username);
        AssertUtil.isTrueThrow(null == userInfo, () -> new AppException(ResultCodeEnum.LOGIN_ERROR));

        //校验密码
        String md5InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        AssertUtil.isTrueThrow(!md5InputPassword.equals(userInfo.getPassword()), () -> new AppException(ResultCodeEnum.LOGIN_ERROR));

        //校验是否被禁用
        AssertUtil.isTrueThrow(userInfo.getStatus() == 0, () -> new AppException(ResultCodeEnum.ACCOUNT_STOP));

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisCache.setCacheObject(RedisContest.USER_TOKEN + token, userInfo, 30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        UserInfo user = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }
}
