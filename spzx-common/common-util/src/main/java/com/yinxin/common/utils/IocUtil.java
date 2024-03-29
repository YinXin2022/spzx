package com.yinxin.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * IocUtil
 *
 * @author ankelen
 * @date 2020-10-13 10:35
 */
public class IocUtil implements ApplicationContextAware {
    private static ApplicationContext ctx;

    private IocUtil() {
    }

    public static Object get(String name) {
        return ctx.getBean(name);
    }

    public static <T> T get(Class<T> clz) {
        return ctx.getBean(clz);
    }


    //region inject

    public static <T> T get(String name, Class<T> clz) {
        return ctx.getBean(name, clz);
    }

    public static <T> Map<String, T> getMap(Class<T> clz) {
        return ctx.getBeansOfType(clz);
    }

    //endregion

    //region singleton

    public static IocUtil getInstance() {
        return IocUtilHolder.INSTANCE;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext ctx) throws BeansException {
        IocUtil.ctx = ctx;
    }

    private static class IocUtilHolder {
        private static final IocUtil INSTANCE = new IocUtil();
    }

    //endregion singleton
}
