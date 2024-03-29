package com.yinxin.spzx.manager.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.utils.AssertUtil;
import com.yinxin.common.utils.SysAuthContextUtil;
import com.yinxin.common.utils.MenuUtil;
import com.yinxin.spzx.manager.mapper.SysMenuMapper;
import com.yinxin.spzx.manager.mapper.SysRoleMenuMapper;
import com.yinxin.spzx.manager.service.SysMenuService;
import com.yinxin.spzx.model.entity.system.SysMenu;
import com.yinxin.spzx.model.entity.system.SysUser;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.system.SysMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author YinXin
 * @date 2024-01-17 16:58
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;


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

        // 新添加一个菜单，那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu);
    }

    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {
        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.findById(sysMenu.getParentId());

        if (parentMenu != null) {
            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu);
        }
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

    @Override
    public Map<String, Object> findMenuByRoleId(Long roleId) {
        Map<String, Object> result = new HashMap<>();
        result.put("sysMenuList", list());
        result.put("roleMenuIds", sysRoleMenuMapper.findByRoleId(roleId));
        return result;
    }

    @Override
    public List<SysMenuVo> findUserMenuList() {
        SysUser sysUser = SysAuthContextUtil.get();
        Long userId = sysUser.getId();

        List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId(userId);
        List<SysMenu> sysMenuTreeList = MenuUtil.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }

    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
