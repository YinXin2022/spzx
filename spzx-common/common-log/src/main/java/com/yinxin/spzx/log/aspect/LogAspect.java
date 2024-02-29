package com.yinxin.spzx.log.aspect;

import com.yinxin.spzx.log.annotation.Log;
import com.yinxin.spzx.log.service.AsyncOperLogService;
import com.yinxin.spzx.log.utils.LogUtil;
import com.yinxin.spzx.model.entity.system.SysOperLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author YinXin
 * @date 2024-02-22 15:16
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LogAspect {
    private final AsyncOperLogService asyncOperLogService;

    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {
        // 构建前置参数
        SysOperLog sysOperLog = new SysOperLog();

        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            LogUtil.afterHandleLog(sysLog, proceed, sysOperLog, 0, null);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandleLog(sysLog, proceed, sysOperLog, 1, e.getMessage());
            throw new RuntimeException();
        }
        asyncOperLogService.saveSysOperLog(sysOperLog);
        return proceed;
    }
}
