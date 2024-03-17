package com.yinxin.pay;

import com.yinxin.common.annotation.EnableRedisCache;
import com.yinxin.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YinXin
 * @date 2024-03-17 8:52
 */
@SpringBootApplication
@EnableRedisCache
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = {"com.yinxin.spzx.order.client"})
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
