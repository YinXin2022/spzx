package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-17 17:42
 */
@Mapper
public interface SysRoleMenuMapper {

    List<Long> findByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void insertByDto(AssignMenuDto assignMenuDto);
}
