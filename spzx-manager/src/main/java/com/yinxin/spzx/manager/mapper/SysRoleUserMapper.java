package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-17 15:28
 */
@Mapper
public interface SysRoleUserMapper {

    void deleteByUserId(Long userId);

    void insert(@Param("userId") Long userId,@Param("roleId") Long roleId);

    List<SysRole> findByUserId(Long userId);
}
