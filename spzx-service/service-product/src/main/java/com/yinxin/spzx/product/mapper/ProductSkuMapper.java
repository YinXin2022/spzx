package com.yinxin.spzx.product.mapper;

import com.yinxin.spzx.model.dto.h5.ProductSkuDto;
import com.yinxin.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-28 15:38
 */
@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    ProductSku getById(Long skuId);

    List<ProductSku> findByProductId(Long productId);
}
