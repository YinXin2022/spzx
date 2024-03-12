package com.yinxin.spzx.cart.client;

import com.yinxin.spzx.model.entity.h5.CartInfo;
import com.yinxin.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-11 18:42
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {
    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    List<CartInfo> getAllCkecked();

    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    Result deleteChecked();
}
