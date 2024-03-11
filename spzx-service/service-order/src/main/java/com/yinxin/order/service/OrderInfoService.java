package com.yinxin.order.service;

import com.yinxin.spzx.model.vo.h5.TradeVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-03-11 18:56
 */
@Service
public interface OrderInfoService {
    TradeVo getTrade();
}
