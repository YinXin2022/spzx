package com.yinxin.spzx.product.mapper;

import com.yinxin.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-02-29 19:19
 */
@Mapper
public interface ProductDetailsMapper {
    ProductDetails getByProductId(Long productId);
}
