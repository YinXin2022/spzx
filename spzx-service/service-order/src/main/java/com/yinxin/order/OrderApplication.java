package com.yinxin.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author YinXin
 * @date 2024-03-11 18:48
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.yinxin.spzx.cart.client"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
