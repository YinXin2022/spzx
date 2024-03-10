package com.yinxin.spzx.product.mapper;

import com.yinxin.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-02-29 19:21
 */
@Mapper
public interface ProductMapper {
    Product getById(Long productId);
}
