package com.yinxin.spzx.manager;

import com.yinxin.spzx.log.annotation.EnableLogAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author YinXin
 * @date 2024-01-12 18:25
 */
@SpringBootApplication
@EnableScheduling
@EnableLogAspect
@ComponentScan("com.yinxin.*")
public class SqzxAppManager {
    public static void main(String[] args) {
        SpringApplication.run(SqzxAppManager.class, args);
    }
}
