package com.yinxin.common.config;

import com.yinxin.common.interceptor.UserLoginAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author YinXin
 * @date 2024-03-10 15:46
 */
@Configuration
@RequiredArgsConstructor
public class UserWebMvcConfiguration implements WebMvcConfigurer {
    private final UserLoginAuthInterceptor userLoginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/userInfo/login");
    }
}
