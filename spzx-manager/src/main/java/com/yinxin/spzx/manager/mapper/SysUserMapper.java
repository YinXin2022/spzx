package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.dto.system.SysUserDto;
import com.yinxin.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-12 17:21
 */
@Mapper
public interface SysUserMapper {
    SysUser getByUsername(String userName);

    List<SysUser> pageAllByNameAndCreateTime(SysUserDto sysUserDto);

    void insert(SysUser sysUser);

    void update(SysUser sysUser);

    void deleteById(Long id);

    SysUser getById(Long id);
}
