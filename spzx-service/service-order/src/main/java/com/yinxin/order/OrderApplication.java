package com.yinxin.order;

import com.yinxin.common.annotation.EnableRedisCache;
import com.yinxin.common.annotation.EnableUserLoginAuthInterceptor;
import com.yinxin.common.annotation.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YinXin
 * @date 2024-03-11 18:48
 */
@SpringBootApplication
@EnableRedisCache
@EnableUserTokenFeignInterceptor
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = {"com.yinxin.spzx.cart.client","com.yinxin.spzx.user.client","com.yinxin.spzx.product.client"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
