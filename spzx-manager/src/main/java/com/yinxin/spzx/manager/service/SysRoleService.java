package com.yinxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.dto.system.AssignMenuDto;
import com.yinxin.spzx.model.dto.system.SysRoleDto;
import com.yinxin.spzx.model.entity.system.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-13 14:56
 */
@Service
public interface SysRoleService {
    PageInfo<SysRole> page(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void create(SysRole sysRole);

    void update(SysRole sysRole);

    void delete(Long id);

    List<SysRole> list();

    List<SysRole> listByUserId(Long userId);

    void addMenus(AssignMenuDto assignMenuDto);
}
