package com.yinxin.spzx.manager.service;

import com.yinxin.spzx.model.entity.system.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-17 16:58
 */
@Service
public interface SysMenuService {
    List<SysMenu> list();

    void create(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void delete(Long id);
}
