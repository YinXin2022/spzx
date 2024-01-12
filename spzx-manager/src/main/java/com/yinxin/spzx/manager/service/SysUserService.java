package com.yinxin.spzx.manager.service;

import com.yinxin.spzx.model.dto.system.LoginDto;
import com.yinxin.spzx.model.vo.system.LoginVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-01-12 17:22
 */
@Service
public interface SysUserService {
    public LoginVo login(LoginDto loginDto);
}
