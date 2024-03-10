package com.yinxin.common.utils;

import com.yinxin.spzx.model.entity.system.SysUser;

/**
 *
 * @author YinXin
 * @date 2024-01-13 13:52
 */
public class SysAuthContextUtil {
    private final static ThreadLocal<SysUser> SYS_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(SysUser sysUser) {
        SYS_USER_THREAD_LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return SYS_USER_THREAD_LOCAL.get();
    }

    public static void remove() {
        SYS_USER_THREAD_LOCAL.remove();
    }
}
