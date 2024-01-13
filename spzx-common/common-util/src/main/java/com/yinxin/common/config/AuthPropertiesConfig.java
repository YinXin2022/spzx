package com.yinxin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-13 14:16
 */
@Data
@Component
@ConfigurationProperties(prefix = "spzx.auth")
public class AuthPropertiesConfig {
    /**
     * 添加拦截器拦截路径
     */
    private List<String> addPaths = new ArrayList<>();
    /**
     * 添加拦截器不拦截的路径
     */
    private List<String> excludePaths = new ArrayList<>();
}
