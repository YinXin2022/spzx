package com.yinxin.spzx.product;

import com.yinxin.common.annotation.EnableRedisCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YinXin
 * @date 2024-02-28 15:09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisCache
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
