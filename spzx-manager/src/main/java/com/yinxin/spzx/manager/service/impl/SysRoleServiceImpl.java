package com.yinxin.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.mapper.SysRoleMapper;
import com.yinxin.spzx.manager.service.SysRoleService;
import com.yinxin.spzx.model.dto.system.SysRoleDto;
import com.yinxin.spzx.model.entity.system.SysRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-13 14:57
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> page(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> sysRoleList = sysRoleMapper.pageAllByName(sysRoleDto) ;
        PageInfo<SysRole> pageInfo = new PageInfo(sysRoleList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        sysRoleMapper.delete(id);
    }
}
