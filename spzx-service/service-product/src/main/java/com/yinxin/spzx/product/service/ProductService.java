package com.yinxin.spzx.product.service;

import com.yinxin.spzx.model.entity.product.ProductSku;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-28 15:28
 */
@Service
public interface ProductService {
    List<ProductSku> findProductSkuBySale();
}
