package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-17 17:00
 */
@Mapper
public interface SysMenuMapper {

    List<SysMenu> findAll();

    void insert(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void deleteById(Long id);

    int countByParentId(Long id);

    List<SysMenu> selectListByUserId(Long userId);
}
