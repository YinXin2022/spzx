package com.yinxin.common.annotation;


import com.yinxin.common.interceptor.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YinXin
 * @date 2024-03-11 19:04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {UserTokenFeignInterceptor.class})
public @interface EnableUserTokenFeignInterceptor {
}
