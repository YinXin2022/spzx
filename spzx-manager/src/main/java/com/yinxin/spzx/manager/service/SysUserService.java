package com.yinxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.dto.system.AssginRoleDto;
import com.yinxin.spzx.model.dto.system.LoginDto;
import com.yinxin.spzx.model.dto.system.SysUserDto;
import com.yinxin.spzx.model.entity.system.SysUser;
import com.yinxin.spzx.model.vo.system.LoginVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-01-12 17:22
 */
@Service
public interface SysUserService {
    public LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> pageBy(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void create(SysUser sysUser);

    void update(SysUser sysUser, boolean predicate);

    void delete(Long id);

    void addRoles(AssginRoleDto assginRoleDto);
}
