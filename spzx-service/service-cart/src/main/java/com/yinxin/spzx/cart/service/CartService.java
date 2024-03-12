package com.yinxin.spzx.cart.service;

import com.yinxin.spzx.model.entity.h5.CartInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-11 16:40
 */
@Service
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> getCartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();

    List<CartInfo> getAllCkecked();

    void deleteChecked();
}
