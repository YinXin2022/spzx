package com.yinxin.order.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.utils.AssertUtil;
import com.yinxin.common.utils.AuthContextUtil;
import com.yinxin.order.mapper.OrderInfoMapper;
import com.yinxin.order.mapper.OrderItemMapper;
import com.yinxin.order.mapper.OrderLogMapper;
import com.yinxin.order.service.OrderInfoService;
import com.yinxin.spzx.cart.client.CartFeignClient;
import com.yinxin.spzx.model.dto.h5.OrderInfoDto;
import com.yinxin.spzx.model.entity.h5.CartInfo;
import com.yinxin.spzx.model.entity.order.OrderInfo;
import com.yinxin.spzx.model.entity.order.OrderItem;
import com.yinxin.spzx.model.entity.order.OrderLog;
import com.yinxin.spzx.model.entity.product.ProductSku;
import com.yinxin.spzx.model.entity.user.UserAddress;
import com.yinxin.spzx.model.entity.user.UserInfo;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.h5.TradeVo;
import com.yinxin.spzx.product.client.ProductFeignClient;
import com.yinxin.spzx.user.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-11 18:57
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {
    private final CartFeignClient cartFeignClient;
    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;
    private final OrderInfoMapper orderInfoMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderLogMapper orderLogMapper;

    @Override
    public TradeVo getTrade() {
        // 获取当前登录的用户的id
        //Long userId = AuthContextUtil.getUserInfo().getId();

        // 获取选中的购物项列表数据
        List<CartInfo> cartInfoList = cartFeignClient.getAllCkecked();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {        // 将购物项数据转换成功订单明细数据
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        // 计算总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        AssertUtil.isTrueThrow(CollectionUtils.isEmpty(orderItemList), () -> new AppException(ResultCodeEnum.DATA_ERROR));
        for (OrderItem orderItem : orderItemList) {
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            AssertUtil.isTrueThrow(productSku == null, () -> new AppException(ResultCodeEnum.DATA_ERROR));
            AssertUtil.isTrueThrow(productSku.getStockNum().intValue() < orderItem.getSkuNum().intValue(), () -> new AppException(ResultCodeEnum.DATA_ERROR));
        }

        // 构建订单数据，保存订单
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        OrderInfo orderInfo = new OrderInfo();
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //用户id
        orderInfo.setUserId(userInfo.getId());
        //用户昵称
        orderInfo.setNickName(userInfo.getNickName());
        //用户收货地址信息
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);
        orderInfoMapper.save(orderInfo);

        //保存订单明细
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }

        //记录日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);

        //远程调用service-cart微服务接口清空购物车数据
        cartFeignClient.deleteChecked();
        return orderInfo.getId();
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.findByOrderId(orderId);
    }

    @Override
    public TradeVo buy(Long skuId) {
        // 查询商品
        ProductSku productSku = productFeignClient.getBySkuId(skuId);
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItemList.add(orderItem);

        // 计算总金额
        BigDecimal totalAmount = productSku.getSalePrice();
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);

        // 返回
        return tradeVo;
    }

    @Override
    public PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page, limit);
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.findUserPage(userId, orderStatus);

        orderInfoList.forEach(orderInfo -> {
            List<OrderItem> orderItem = orderItemMapper.findByOrderId(orderInfo.getId());
            orderInfo.setOrderItemList(orderItem);
        });

        return new PageInfo<>(orderInfoList);
    }
}
