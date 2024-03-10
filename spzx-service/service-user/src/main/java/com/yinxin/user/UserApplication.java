package com.yinxin.user;

import com.yinxin.common.annotation.EnableRedisCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YinXin
 * @date 2024-02-29 19:53
 */
@SpringBootApplication
@EnableRedisCache
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
