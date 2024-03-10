package com.yinxin.common.interceptor;

import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.common.utils.AuthContextUtil;
import com.yinxin.spzx.model.entity.user.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author YinXin
 * @date 2024-03-10 15:40
 */
@Component
@RequiredArgsConstructor
public class UserLoginAuthInterceptor implements HandlerInterceptor {
    private final RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo = redisCache.getCacheObject(RedisContest.USER_TOKEN + request.getHeader("token"), UserInfo.class);
        AuthContextUtil.setUserInfo(userInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AuthContextUtil.removeUserInfo();
    }
}
