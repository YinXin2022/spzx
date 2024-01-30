package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-25 17:31
 */
@Mapper
public interface SysProductSpecMapper {

    List<ProductSpec> pageAll(Integer pageNum, Integer pageSize);

    void save(ProductSpec productSpec);

    void update(ProductSpec productSpec);

    void delete(Long id);
}
