package com.yinxin.common.annotation;

import com.yinxin.common.config.RedisConfig;
import com.yinxin.common.redis.RedisCache;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YinXin
 * @date 2024-02-29 20:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {RedisCache.class, RedisConfig.class})
public @interface EnableRedisCache {
}
