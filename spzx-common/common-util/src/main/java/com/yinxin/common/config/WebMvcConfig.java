package com.yinxin.common.config;

import com.yinxin.common.interceptor.LoginInterceptor;
import com.yinxin.common.utils.IocUtil;
import com.yinxin.common.utils.MinIoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author YinXin
 * @date 2024-01-12 20:32
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AuthPropertiesConfig authPropertiesConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(authPropertiesConfig.getAddPaths())
                .excludePathPatterns(authPropertiesConfig.getExcludePaths());
    }

    @Bean
    @Lazy(value = false)
    @ConditionalOnMissingBean
    IocUtil iocUtil() {
        return IocUtil.getInstance();
    }

    @Bean
    @Lazy(value = false)
    @ConditionalOnMissingBean
    MinIoUtil minIoUtil() {
        return MinIoUtil.getInstance();
    }
}
