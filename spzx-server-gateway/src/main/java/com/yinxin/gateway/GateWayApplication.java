package com.yinxin.gateway;

import com.yinxin.common.annotation.EnableRedisCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YinXin
 * @date 2024-02-29 16:13
 */
@SpringBootApplication
@EnableRedisCache
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
