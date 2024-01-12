package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-01-12 17:21
 */
@Mapper
public interface SysUserMapper {
    SysUser getByUsername(String userName);
}
