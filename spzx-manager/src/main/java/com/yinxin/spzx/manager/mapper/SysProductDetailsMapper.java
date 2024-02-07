package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-02-07 14:44
 */
@Mapper
public interface SysProductDetailsMapper {
    void save(ProductDetails productDetails);

    ProductDetails findByProductId(Long id);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}
