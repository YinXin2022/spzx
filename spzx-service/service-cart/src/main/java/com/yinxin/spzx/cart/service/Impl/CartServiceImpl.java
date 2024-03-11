package com.yinxin.spzx.cart.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.common.utils.AuthContextUtil;
import com.yinxin.spzx.cart.service.CartService;
import com.yinxin.spzx.model.entity.h5.CartInfo;
import com.yinxin.spzx.model.entity.product.ProductSku;
import com.yinxin.spzx.product.client.ProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YinXin
 * @date 2024-03-11 16:40
 */
@Service
@Transactional(readOnly = true) // 购物车数据通过redis存储，使用hash数据结构（key: userId keyName: skuId value: json）
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final RedisCache redisCache;
    private final ProductFeignClient productFeignClient;

    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        Object cartInfoObj = redisCache.getCacheMapValue(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null;
        if (cartInfoObj != null) {       //  如果购物车中有该商品，则商品数量 相加！
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {
            // 当购物车中没用该商品的时候，则直接添加到购物车！
            cartInfo = new CartInfo();
            // 购物车数据是从商品详情得到 {skuInfo}
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }
        redisCache.setCacheMapValue(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> getCartList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        List<Object> cartInfoList = redisCache.getCacheMapValues(cartKey);
        if (!CollectionUtils.isEmpty(cartInfoList)) {
            List<CartInfo> infoList = cartInfoList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return infoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteCart(Long skuId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        redisCache.deleteCacheMapValue(cartKey, String.valueOf(skuId));
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        Boolean hasKey = redisCache.hasHashKey(cartKey, String.valueOf(skuId));
        if (hasKey) {
            String cartInfoJSON = redisCache.getCacheMapValue(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            redisCache.setCacheMapValue(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        List<Object> cartItems = redisCache.getCacheMapValues(cartKey);
        if (!CollectionUtils.isEmpty(cartItems)) {
            cartItems.stream().map(cartItem -> {
                CartInfo cartInfo = JSON.parseObject(cartItem.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                return cartInfo;
            }).forEach(cartInfo -> redisCache.setCacheMapValue(cartKey, cartInfo.getSkuId().toString(), JSON.toJSONString(cartInfo)));
        }
    }

    @Override
    public void clearCart() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        redisCache.deleteObject(cartKey);
    }

    @Override
    public List<CartInfo> getAllCkecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = RedisContest.USER_CART + userId;
        List<Object> cartItems = redisCache.getCacheMapValues(cartKey);
        List<CartInfo> cartInfos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cartItems)) {
            cartInfos = cartItems.stream()
                    .map(cartItem -> JSON.parseObject(cartItem.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
        }
        return cartInfos;
    }
}
