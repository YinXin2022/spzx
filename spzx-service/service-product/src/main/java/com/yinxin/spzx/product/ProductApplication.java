package com.yinxin.spzx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author YinXin
 * @date 2024-02-28 15:09
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = "com.yinxin.*", excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.yinxin.common.config.WebMvcConfig"))
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
