package com.yinxin.spzx.manager.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.utils.AssertUtil;
import com.yinxin.common.utils.MenuUtil;
import com.yinxin.spzx.manager.mapper.SysMenuMapper;
import com.yinxin.spzx.manager.service.SysMenuService;
import com.yinxin.spzx.model.entity.system.SysMenu;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-17 16:58
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> list() {
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        if (CollectionUtils.isEmpty(sysMenuList)) return null;
        List<SysMenu> treeList = MenuUtil.buildTree(sysMenuList); //构建树形数据
        return treeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        int count = sysMenuMapper.countByParentId(id);
        AssertUtil.isTrueThrow(count > 0, () -> new AppException(ResultCodeEnum.NODE_ERROR));
        sysMenuMapper.deleteById(id);
    }
}
