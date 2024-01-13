package com.yinxin.common.utils;

import java.util.function.Supplier;

/**
 * 断言工具类
 * @author YinXin
 * @date 2024-01-13 17:18
 */
public class AssertUtil {
    /**
     * 表达式为真时，抛出异常
     */
    public static void isTrueThrow(boolean expression, Supplier<? extends RuntimeException> throwableSupplier) {
        if (expression) {
            throw throwableSupplier.get();
        }
    }
    /**
     * 表达式为假时，抛出异常
     */
    public static void isFalseThrow(boolean expression, Supplier<? extends RuntimeException> throwableSupplier) {
        if (!expression) {
            throw throwableSupplier.get();
        }
    }
}
