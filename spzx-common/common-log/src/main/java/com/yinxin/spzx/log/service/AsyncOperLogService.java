package com.yinxin.spzx.log.service;

import com.yinxin.spzx.model.entity.system.SysOperLog;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-02-22 15:48
 */
@Service
public interface AsyncOperLogService {
    void saveSysOperLog(SysOperLog sysOperLog) ;
}
