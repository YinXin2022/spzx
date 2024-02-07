package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-07 14:43
 */
@Mapper
public interface SysProductSkuMapper {
    void save(ProductSku productSku);

    List<ProductSku> findByProductId(Long id);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}
