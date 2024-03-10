package com.yinxin.user;

import com.yinxin.common.annotation.EnableRedisCache;
import com.yinxin.common.annotation.EnableUserLoginAuthInterceptor;
import com.yinxin.common.exception.handler.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author YinXin
 * @date 2024-02-29 19:53
 */
@SpringBootApplication
@Import(GlobalExceptionHandler.class)
@EnableUserLoginAuthInterceptor
@EnableRedisCache
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
