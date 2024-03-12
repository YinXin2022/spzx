package com.yinxin.spzx.user.client;

import com.yinxin.spzx.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YinXin
 * @date 2024-03-12 15:26
 */
@FeignClient("service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    UserAddress getUserAddress(@PathVariable Long id);
}
