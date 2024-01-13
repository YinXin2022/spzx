package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.dto.system.SysRoleDto;
import com.yinxin.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-13 14:58
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> pageAllByName(SysRoleDto sysRoleDto);
}
