package com.yinxin.spzx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YinXin
 * @date 2024-01-12 18:25
 */
@SpringBootApplication
@ComponentScan("com.yinxin.*")
public class SqzxAppManager {
    public static void main(String[] args) {
        SpringApplication.run(SqzxAppManager.class, args);
    }
}
