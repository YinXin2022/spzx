package com.yinxin.spzx.manager.service.impl;

import com.yinxin.spzx.log.service.AsyncOperLogService;
import com.yinxin.spzx.manager.mapper.SysOperLogMapper;
import com.yinxin.spzx.model.entity.system.SysOperLog;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YinXin
 * @date 2024-02-22 15:31
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    private final SysOperLogMapper sysOperLogMapper;

    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
