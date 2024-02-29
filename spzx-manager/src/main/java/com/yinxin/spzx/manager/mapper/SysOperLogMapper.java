package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-02-22 15:41
 */
@Mapper
public interface SysOperLogMapper {
    void insert(SysOperLog sysOperLog);
}
