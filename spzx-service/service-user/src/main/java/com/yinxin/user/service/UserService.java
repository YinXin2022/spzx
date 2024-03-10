package com.yinxin.user.service;

import com.yinxin.spzx.model.dto.h5.UserLoginDto;
import com.yinxin.spzx.model.dto.h5.UserRegisterDto;
import com.yinxin.spzx.model.vo.h5.UserInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-03-10 13:54
 */
@Service
public interface UserService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
